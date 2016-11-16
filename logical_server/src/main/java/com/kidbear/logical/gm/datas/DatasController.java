package com.kidbear.logical.gm.datas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kidbear.logical.manager.log.LogMgr;
import com.kidbear.logical.manager.log.RemainInfo;
import com.kidbear.logical.util.redis.Redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/datas")
public class DatasController {
    public Logger logger = LoggerFactory.getLogger(DatasController.class);

    @RequestMapping(value = "/class")
    public void classList(HttpServletRequest request, Model model) {

    }

    @RequestMapping(value = "/thread")
    public void thread(HttpServletRequest request, Model model) {

    }

    @RequestMapping(value = "/ipmaplogHandle")
    @ResponseBody
    public String ipmaplogHandle(HttpServletRequest request) {
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.GLOBAL_DB, LogMgr.IP_LOG);
        JSONArray ret = new JSONArray();
        for (String ip : logMap.keySet()) {
            String country = ip.split("#").length > 0 ? ip.split("#")[0] : "";
            String province = ip.split("#").length > 1 ? ip.split("#")[1] : "";
            String city = ip.split("#").length > 2 ? ip.split("#")[2] : "";
            if (country.length() == 0 || province.length() == 0
                    || city.length() == 0 || !country.equals("中国")
                    || province.equals("中国")) {
                continue;
            }
            JSONObject cityJson = new JSONObject();
            cityJson.put("name", city);
            cityJson.put("value", Integer.parseInt(logMap.get(ip)));
            ret.add(cityJson);
        }
        return ret.toJSONString();
    }

    @RequestMapping(value = "/paycostlog")
    public void paycostlog(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.PAY_COST_LOG);
        for (String payType : logMap.keySet()) {
            String[] arr = new String[]{payType, logMap.get(payType)};
            logs.add(arr);
        }
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/paycostlogHandle")
    @ResponseBody
    public String paycostlogHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.PAY_COST_LOG);
        for (String yuanbaoType : logMap.keySet()) {
            String[] arr = new String[]{yuanbaoType, logMap.get(yuanbaoType)};
            logs.add(arr);
        }
        JSONObject ret = new JSONObject();
        List<String> payTypeCharts = new ArrayList<String>();
        List<String> countCharts = new ArrayList<String>();
        for (String[] arr : logs) {
            countCharts.add(arr[1]);
        }
        ret.put("countCharts", countCharts);
        ret.put("payTypeCharts", payTypeCharts);
        return ret.toJSONString();
    }

    @RequestMapping(value = "/activelog")
    public void activelog(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.ACTIVE_LOG);
        for (String date : logMap.keySet()) {
            String[] arr = new String[]{date, logMap.get(date)};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/activelogHandle")
    @ResponseBody
    public String activelogHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.ACTIVE_LOG);
        for (String date : logMap.keySet()) {
            String[] arr = new String[]{date, logMap.get(date)};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        JSONObject ret = new JSONObject();
        List<String> dateCharts = new ArrayList<String>();
        List<String> countCharts = new ArrayList<String>();
        for (String[] arr : logs) {
            dateCharts.add(arr[0]);
            countCharts.add(arr[1]);
        }
        ret.put("countCharts", countCharts);
        ret.put("dateCharts", dateCharts);
        return ret.toJSONString();
    }

    @RequestMapping(value = "/dailypaylog")
    public void dailypaylog(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.DAILY_PAY_LOG);
        for (String date : logMap.keySet()) {
            String[] arr = new String[]{date, logMap.get(date)};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/dailypaylogHandle")
    @ResponseBody
    public String dailypaylogHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.DAILY_PAY_LOG);
        for (String date : logMap.keySet()) {
            String[] arr = new String[]{date, logMap.get(date)};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        JSONObject ret = new JSONObject();
        List<String> dateCharts = new ArrayList<String>();
        List<String> countCharts = new ArrayList<String>();
        for (String[] arr : logs) {
            dateCharts.add(arr[0]);
            countCharts.add(arr[1]);
        }
        ret.put("countCharts", countCharts);
        ret.put("dateCharts", dateCharts);
        return ret.toJSONString();
    }

    Comparator<String[]> dateComparator = new Comparator<String[]>() {

        @Override
        public int compare(String[] o1, String[] o2) {
            int year1 = Integer.parseInt(o1[0].split("-")[0]);
            int month1 = Integer.parseInt(o1[0].split("-")[1]);
            int day1 = Integer.parseInt(o1[0].split("-")[2]);
            int year2 = Integer.parseInt(o2[0].split("-")[0]);
            int month2 = Integer.parseInt(o2[0].split("-")[1]);
            int day2 = Integer.parseInt(o2[0].split("-")[2]);
            if (year1 > year2) {
                return 1;
            } else if (year1 == year2) {
                if (month1 > month2) {
                    return 1;
                } else if (month1 == month2) {
                    if (day1 > day2) {
                        return 1;
                    } else if (day1 == day2) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    };

    @RequestMapping(value = "/remain7log")
    public void remain7log(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + "",
                    remainInfo.getSeventhCount() + "", ""};
            double remainDouble = Double.parseDouble(arr[2])
                    / Double.parseDouble(arr[1]);
            remainDouble = (double) Math.round(remainDouble * 10000) / 10000;
            arr[3] = (remainDouble * 100) + "%";
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/remain7logHandle")
    @ResponseBody
    public String remain7logHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + "",
                    remainInfo.getSeventhCount() + ""};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        JSONObject ret = new JSONObject();
        List<String> dateCharts = new ArrayList<String>();
        List<String> remain7Charts = new ArrayList<String>();
        for (String[] arr : logs) {
            dateCharts.add(arr[0]);
            double remainDouble = Double.parseDouble(arr[2])
                    / Double.parseDouble(arr[1]);
            remainDouble = (double) Math.round(remainDouble * 10000) / 10000;
            remain7Charts.add((remainDouble * 100) + "");
        }
        ret.put("remain7Charts", remain7Charts);
        ret.put("dateCharts", dateCharts);
        return ret.toJSONString();
    }

    @RequestMapping(value = "/remain2log")
    public void remain2log(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + "",
                    remainInfo.getSecondCount() + "", ""};
            double remainDouble = Double.parseDouble(arr[2])
                    / Double.parseDouble(arr[1]);
            remainDouble = (double) Math.round(remainDouble * 10000) / 10000;
            arr[3] = (remainDouble * 100) + "%";
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/remain2logHandle")
    @ResponseBody
    public String remain2logHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + "",
                    remainInfo.getSecondCount() + ""};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        JSONObject ret = new JSONObject();
        List<String> dateCharts = new ArrayList<String>();
        List<String> remain2Charts = new ArrayList<String>();
        for (String[] arr : logs) {
            dateCharts.add(arr[0]);
            double remainDouble = Double.parseDouble(arr[2])
                    / Double.parseDouble(arr[1]);
            remainDouble = (double) Math.round(remainDouble * 10000) / 10000;
            remain2Charts.add((remainDouble * 100) + "");
        }
        ret.put("remain2Charts", remain2Charts);
        ret.put("dateCharts", dateCharts);
        return ret.toJSONString();
    }

    @RequestMapping(value = "/createlog")
    public void createlog(HttpServletRequest request, Model model) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + ""};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        model.addAttribute("logs", logs);
    }

    @RequestMapping(value = "/createlogHandle")
    @ResponseBody
    public String createlogHandle(HttpServletRequest request) {
        List<String[]> logs = new ArrayList<String[]>();
        Map<String, String> logMap = Redis.getInstance().hgetAll(
                Redis.LOGIC_DB, LogMgr.REMAIN_LOG);
        for (String date : logMap.keySet()) {
            RemainInfo remainInfo = JSON.parseObject(logMap.get(date),
                    RemainInfo.class);
            String[] arr = new String[]{date,
                    remainInfo.getCreateCount() + ""};
            logs.add(arr);
        }
        Collections.sort(logs, dateComparator);
        JSONObject ret = new JSONObject();
        List<String> dateCharts = new ArrayList<String>();
        List<String> createCharts = new ArrayList<String>();
        for (String[] arr : logs) {
            dateCharts.add(arr[0]);
            createCharts.add(arr[1]);
        }
        ret.put("createCharts", createCharts);
        ret.put("dateCharts", dateCharts);
        return ret.toJSONString();
    }

}
