package com.company;

public class ArrayNumber {

    public static int[] addZero(int[] a,int len)
    {
        int[] res = new int[len];
        int resL = res.length-1;
        for(int i = a.length-1;i>=0;i--)
        {
            res[resL] = a[i];
            resL--;
        }
        while(resL >= 0)
        {
            res[resL]=0;
            resL--;
        }
        return res;
    }


    public static boolean isGreaterEquel(int[]a,int[]b)
    {
        int max = Math.max(a.length,b.length);
        int[] A = addZero(a,max);
        int[] B = addZero(b,max);
        for (int i = 0; i<max;i++)
        {
            if (A[i]>B[i])
                return true;
            if (A[i]==B[i])
                continue;
            if (A[i]<B[i])
                return false;
        }
        return true;
    }

    public static boolean isLesser(int[]a)
    {
        for (int i = 0; i< a.length; i++)
        {
            if(a[i]<0)
                return true;
        }

        return false;
    }

    public static int[] sum(int[] a, int[] b)
    {
        int max = Math.max(a.length,b.length);
        a = addZero(a,max);
        b = addZero(b,max);
        int[] res = new int[a.length+1];

        int aL = a.length-1;
        int bL = b.length-1;
        int rL = res.length-1;
        int p = 0;

        while (aL>=0)
        {
            res[rL] = (a[aL]+b[bL]+p)%256;
            p = (a[aL]+b[bL]+p)/256;
            rL--;
            aL--;
            bL--;
        }
        res[rL]=p;
        return removeZero(res);
    }

    public static int[] sum(int[] a, int[] b, int[] m)
    {
        int[] res = sum(a,b);
        while(isGreaterEquel(res,m))
            res = sub(res,m);
        return res;
    }

    public static int[] sub(int[] a, int[] b, int[] m)
    {
        int[] res = sub(a,b);
        while(isLesser(res))
            res = sum(res,m);
        return res;
    }

    public static int mod(int a, int m)
    {
        int r = a%m;
        if (r < 0)
        {
            r = r + m;
        }
        return r;
    }

//    public static int[] sub(int[]a, int[]b)
//    {
//        int max = Math.max(a.length,b.length);
//        a = addZero(a,max);
//        b = addZero(b,max);
//        int[] res = new int[a.length+1];
//
//        int aL = a.length-1;
//        int rL = res.length-1;
//        int p = 0;
//
//        while (aL>=0)
//        {
//            res[rL] = mod(a[aL]-b[aL]+p,256);
//
//            int dif = a[aL]-b[aL]+p;
//            if(dif==0)
//            {
//                if (a[aL]==0)
//                {
//                    if (b[aL]==0)
//                    {
//                        p = 0;
//                        rL--;
//                        aL--;
//                        continue;
//                    }else
//                    {
//                        p = -1;
//                        rL--;
//                        aL--;
//                        continue;
//                    }
//                }
//                else
//                {
//                    p = 0;
//                    rL--;
//                    aL--;
//                    continue;
//                }
//
//            }
//            else
//                p = (a[aL]-b[aL]+p-256)/256;
//            rL--;
//            aL--;
//        }
//        res[rL]=a[rL]-b[rL]+p;
//        //System.out.println(toLine(res));
//        return removeZero(res);
//    }


    public static int[] sub (int[]a, int[]b)
    {
        int max  = Math.max(a.length,b.length);
        a = addZero(a,max);
        b = addZero(b,max);

        int[] res = new int [max+1];

        int mL = max - 1;
        int rL = max;
        while (mL >=0)
        {
            if (a[mL]==-1)
            {
                a[mL-1]--;
                a[mL]=255;
            }

            int dif = a[mL]-b[mL];

            if (dif < 0)
            {
                res[rL]=dif+256;
                a[mL-1]--;

            }else
            {
                res[rL]=dif;
            }
            mL--;
            rL--;
        }
        res[rL]=a[rL]-b[rL];
        return removeZero(res);

    }

    public static int[] mul(int[]a,int[]b,int[]m)
    {
        String bin = getBinary(b);
        int[] mm = a.clone();

        int[] res;

        if (bin.charAt(0)=='1')
            res = a.clone();
        else
            res = new int[1];

        for (int i = 1; i<bin.length(); i++)
        {
            mm = sum(mm,mm,m);
            //System.out.println((i-1)+" = "+toLine(mm));
            if (bin.charAt(i)=='1')
                res = sum(res,mm,m);
        }
        return res;
    }


    public static int[] pow (int[]a, int[]b, int[]m)
    {
        String bin = getBinary(b);
        System.out.println(bin);
        int[] res;
        if (bin.charAt(0)=='1')
        {
            res = a.clone();
        }else
        {
            res = new int[1];
            res[0] = 1;
        }

        int[] mm = a.clone();

        for (int i = 1;i < bin.length();i++)
        {
            mm = mul(mm,mm,m);
            if (bin.charAt(i)=='1')
                res = mul(res,mm,m);
        }
        return res;
    }



    public static String getBinary(int[] a)
    {
        String res = "";
        for (int i = a.length-1; i >= 0; i--)
        {
            int value = a[i];
            for (int j = 0; j < 8; j++)
            {
                res+=value%2;
                value = value>>1;
            }
        }
        return res;

    }


    public static int[] removeZero(int[] a)
    {
        int z = 0;
        for (int i = 0; i < a.length;i++)
            if (a[i] == 0) z++;
            else break;

        int[] res = new int[a.length-z];
        for (int i = 0; z < a.length;i++,z++)
            res[i]=a[z];
        return res;
    }

    public static String toLine(int[] a)
    {
        String res = "";
        for (int i = 0; i < a.length; i++)
        {
            res+= a[i]+" ";
        }
        return res;
    }

}
