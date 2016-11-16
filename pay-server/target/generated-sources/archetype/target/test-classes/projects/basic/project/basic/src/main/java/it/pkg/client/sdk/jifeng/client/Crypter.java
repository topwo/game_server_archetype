/*
* LumaQQ - Java QQ Client
*
* Copyright (C) 2004 luma <stubma@163.com>
*                    notXX
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package it.pkg.client.sdk.jifeng.client;

import java.io.ByteArrayOutputStream;
import java.util.Random;


/**
 * ************************************ 
 * @description：加密算法
 * @author 
 * @time 2013-3-6 下午7:03:45
 * @version 
 * copyright mAPPn
 ************************************
*
 */
public class Crypter {
    private byte[] plain;
    private byte[] prePlain;
    private byte[] out;
    private int crypt, preCrypt;
    private int pos;     
    private int padding;
    private byte[] key;
    private boolean header = true;
    private int contextStart;
    private static Random random = new Random();
	private ByteArrayOutputStream baos;
	
	public Crypter() {
		baos = new ByteArrayOutputStream(8);
	}
	
	private static long getUnsignedInt(byte[] in, int offset, int len) {
		long ret = 0;
		int end = 0;
		if (len > 8)
			end = offset + 8;
		else
			end = offset + len;
		for (int i = offset; i < end; i++) {
			ret <<= 8;
			ret |= in[i] & 0xff;
		}
		return (ret & 0xffffffffl) | (ret >>> 32);
	}
    
    public byte[] decrypt(byte[] in, int offset, int len, byte[] k) {
    	if(k == null)
    		return null;
    	
        crypt = preCrypt = 0;
        this.key = k;
        int count;
        byte[] m = new byte[offset + 8];
        
        if((len % 8 != 0) || (len < 16)) return null;
        prePlain = decipher(in, offset);
        pos = prePlain[0] & 0x7;
        count = len - pos - 10;
        if(count < 0) return null;
        
        for(int i = offset; i < m.length; i++)
            m[i] = 0;
        out = new byte[count];
        preCrypt = 0;
        crypt = 8;
        contextStart = 8;
        pos++;
        
        padding = 1;
        while(padding <= 2) {
            if(pos < 8) {
                pos++;
                padding++;
            }
            if(pos == 8) {
                m = in;
                if(!decrypt8Bytes(in, offset, len)) return null;
            }
        }
        
        int i = 0;
        while(count != 0) {
            if(pos < 8) {
                out[i] = (byte)(m[offset + preCrypt + pos] ^ prePlain[pos]);
                i++;
                count--;
                pos++;
            }
            if(pos == 8) {
                m = in;
                preCrypt = crypt - 8;
                if(!decrypt8Bytes(in, offset, len)) 
                    return null;
            }
        }
        for(padding = 1; padding < 8; padding++) {
            if(pos < 8) {
                if((m[offset + preCrypt + pos] ^ prePlain[pos]) != 0)
                    return null;
                pos++;
            }
            if(pos == 8) {
                m = in;
                preCrypt = crypt;
                if(!decrypt8Bytes(in, offset, len)) 
                    return null;
            }
        }
        return out;
    }
    
    public byte[] decrypt(byte[] in, byte[] k) {   
        return decrypt(in, 0, in.length, k);
    }
    
    public byte[] encrypt(byte[] in, int offset, int len, byte[] k) {
    	if(k == null)
    		return in;
    	
        plain = new byte[8];
        prePlain = new byte[8];
        pos = 1;           
        padding = 0; 
        crypt = preCrypt = 0;
        this.key = k;
        header = true;
        
        pos = (len + 0x0A) % 8;
        if(pos != 0)
            pos = 8 - pos;
        out = new byte[len + pos + 10];
        plain[0] = (byte)((rand() & 0xF8) | pos);
        
        for(int i = 1; i <= pos; i++)
            plain[i] = (byte)(rand() & 0xFF);
        pos++;
        for(int i = 0; i < 8; i++)
            prePlain[i] = 0x0;
        
        padding = 1;
        while(padding <= 2) {
            if(pos < 8) {
                plain[pos++] = (byte)(rand() & 0xFF);
                padding++;
            }
            if(pos == 8)
                encrypt8Bytes();
        }
        
        int i = offset;
        while(len > 0) {
            if(pos < 8) {
                plain[pos++] = in[i++];
                len--;
            }
            if(pos == 8)
                encrypt8Bytes();
        }
        
        padding = 1;
        while(padding <= 7) {
            if(pos < 8) {
                plain[pos++] = 0x0;
                padding++;
            }
            if(pos == 8)
                encrypt8Bytes();
        }
        
        return out;
    }

    public byte[] encrypt(byte[] in, byte[] k) {
        return encrypt(in, 0, in.length, k);
    }

    private byte[] encipher(byte[] in) {
        int loop = 0x10;
        long y = getUnsignedInt(in, 0, 4);
        long z = getUnsignedInt(in, 4, 4);
        long a = getUnsignedInt(key, 0, 4);
        long b = getUnsignedInt(key, 4, 4);
        long c = getUnsignedInt(key, 8, 4);
        long d = getUnsignedInt(key, 12, 4);
        long sum = 0;
        long delta = 0x9E3779B9;
        delta &= 0xFFFFFFFFL;

        while (loop-- > 0) {
            sum += delta;
            sum &= 0xFFFFFFFFL;
            y += ((z << 4) + a) ^ (z + sum) ^ ((z >>> 5) + b);
            y &= 0xFFFFFFFFL;
            z += ((y << 4) + c) ^ (y + sum) ^ ((y >>> 5) + d);
            z &= 0xFFFFFFFFL;
        }

        baos.reset();
        writeInt((int)y);
        writeInt((int)z);
        return baos.toByteArray();
    }
    
    private byte[] decipher(byte[] in, int offset) {
        int loop = 0x10;
        long y = getUnsignedInt(in, offset, 4);
        long z = getUnsignedInt(in, offset + 4, 4);
        long a = getUnsignedInt(key, 0, 4);
        long b = getUnsignedInt(key, 4, 4);
        long c = getUnsignedInt(key, 8, 4);
        long d = getUnsignedInt(key, 12, 4);
        long sum = 0xE3779B90;
        sum &= 0xFFFFFFFFL;
        long delta = 0x9E3779B9;
        delta &= 0xFFFFFFFFL;

        while(loop-- > 0) {
            z -= ((y << 4) + c) ^ (y + sum) ^ ((y >>> 5) + d);
            z &= 0xFFFFFFFFL;
            y -= ((z << 4) + a) ^ (z + sum) ^ ((z >>> 5) + b);
            y &= 0xFFFFFFFFL;
            sum -= delta;
            sum &= 0xFFFFFFFFL;
        }

        baos.reset();
        writeInt((int)y);
        writeInt((int)z);
        return baos.toByteArray();
    }
    
    private void writeInt(int t) {
        baos.write(t >>> 24);
        baos.write(t >>> 16);
        baos.write(t >>> 8);
        baos.write(t);
    }
    
    private byte[] decipher(byte[] in) {
        return decipher(in, 0);
    }
    
    private void encrypt8Bytes() {
        for(pos = 0; pos < 8; pos++) {
            if(header) 
            	plain[pos] ^= prePlain[pos];
            else
            	plain[pos] ^= out[preCrypt + pos];
        }
        byte[] crypted = encipher(plain);
        System.arraycopy(crypted, 0, out, crypt, 8);
        
        for(pos = 0; pos < 8; pos++)
            out[crypt + pos] ^= prePlain[pos];
        System.arraycopy(plain, 0, prePlain, 0, 8);
        
        preCrypt = crypt;
        crypt += 8;      
        pos = 0;
        header = false;            
    }

    private boolean decrypt8Bytes(byte[] in , int offset, int len) {
        for(pos = 0; pos < 8; pos++) {
            if(contextStart + pos >= len) 
                return true;
            prePlain[pos] ^= in[offset + crypt + pos];
        }
        
        prePlain = decipher(prePlain);
        if(prePlain == null)
        	return false;
        
        contextStart += 8;
        crypt += 8;
        pos = 0;
        return true;
    }
    
    private int rand() {
        return random.nextInt();
    }
}
