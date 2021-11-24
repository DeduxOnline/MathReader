import java.util.Scanner;
import java.util.ArrayList;
public class MathReader2 {
    public static void main(String[] args) {//(`4+3)*((-2-1)+(10/5))^2+15%-1/2
        String s1 = "", s2, numst="", indexhub="";
        ArrayList<Double> num = new ArrayList<>();
        ArrayList<Character> op = new ArrayList<>();
        ArrayList<String> bks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Input a equation: ");
        String equ = in.next();
        String main = equ.replaceAll(" ", "");
        int end =0, f1=0,f2, index=0, havebk=0, hbk = 0;
        if (main.contains("(")) {
            havebk=1;
            int b=0;
            bks.add(main);
            for (int d=0; d<main.length();d++) {if (main.charAt(d)=='('){b++;}}
            int[] bk1 = new int[b];
            int[] bk2 = new int[b];
            int i = 0, j = 0;
            char h ='?';
            while (main.indexOf("(") != -1 && main.indexOf(")") != -1) {
                if (main.indexOf("(") != -1) {
                    bk1[i] = main.indexOf("(");
                    i++;
                    char[] chars = main.toCharArray();
                    chars[main.indexOf("(")] = h;
                    main = String.valueOf(chars);
                }
                if (main.indexOf(")") != -1) {
                    bk2[j] = main.indexOf(")");
                    j++;
                    char[] chars = main.toCharArray();
                    chars[main.indexOf(")")] = h;
                    main = String.valueOf(chars);
                }
            }
            int ibk1=0,ibk2=0;
            int count, fid=0;
            for(i = 0; i < b; i++) {
                for (j = 0;j < b; j++) {
                    count = bk2[i] - bk1[j];
                    if (count > 0) {
                        fid=j;
                        ibk1=bk1[j];
                        ibk2=bk2[i];
                    }
                }
                bk1[fid]=main.length()+1;
                String bkmain = bks.get(0).substring(ibk1+1,ibk2);
                bks.add(bkmain);
            }
        }
        while (end==0){
            if(havebk==1) {
                if (bks.size() - 1 == 0) {
                    f1 = 1;
                    //System.out.println("End1");
                }
                if (bks.get(bks.size() - 1).indexOf('(') != -1) {
                    if (index == 0) {
                        indexhub = "(" + bks.get(bks.size() - 1) + ")";
                        index++;
                    }
                    String backup = bks.get(bks.size() - 1);
                    String backup2 = bks.get(bks.size() - 2);
                    bks.set(bks.size() - 1, backup2);
                    bks.set(bks.size() - 2, backup);
                    hbk++;
                }
                main = bks.get(bks.size() - 1);
            }
            else{
                f1=1;
            }
            //System.out.println(main+" main");
            main = " " + main + " ";
            int k = 0;
            for (int i = 1; i < main.length(); i++) {
                if (main.charAt(i) == '-' && !Character.isDigit(main.charAt(i - 1)) && k == 0) {
                    s1 = "-";
                    k++;
                    i++;
                }
                if (Character.isDigit(main.charAt(i)) || main.charAt(i)=='.') {
                    s2 = s1 + main.charAt(i);
                    s1 = s2;
                } else {
                    if (s1 != "") {
                        num.add(Double.valueOf(s1));
                    }
                    if (main.charAt(i) != ' ') {
                        op.add(main.charAt(i));
                    }
                    k = 0;
                    s1 = "";
                }
            }
            f2 = 0;
            Double x;
            /**for (Double numbers : num){
                System.out.println(numbers);
            }
            for (Character options : op){
                System.out.println(options);
            }**/
            while (op.size()>0) {
                int per = op.indexOf('%');
                int sqr = op.indexOf('`');
                int deg = op.indexOf('^');
                int div = op.indexOf('/');
                int mul = op.indexOf('*');
                int sup = op.indexOf('+');
                int sub = op.indexOf('-');
                    if (per != -1) {
                        x = num.get(per)/100;
                        num.set(per, x);
                        op.remove(per);
                        continue;
                    }
                    if (sqr != -1) {
                        x = Math.sqrt(num.get(sqr));
                        num.set(sqr, x);
                        op.remove(sqr);
                        continue;
                    }
                    if (deg != -1) {
                        x = Math.pow(num.get(deg), num.get(deg + 1));
                        num.set(deg, x);
                        num.remove(deg + 1);
                        op.remove(deg);
                        continue;
                    }
                    if (div != -1) {
                        x = num.get(div) / num.get(div + 1);
                        num.set(div, x);
                        num.remove(div + 1);
                        op.remove(div);
                        continue;
                    }
                    if (mul != -1) {
                        x = num.get(mul) * num.get(mul + 1);
                        num.set(mul, x);
                        num.remove(mul + 1);
                        op.remove(mul);
                        continue;
                    }
                    if (sub != -1) {
                        x = num.get(sub) - num.get(sub + 1);
                        num.set(sub, x);
                        num.remove(sub + 1);
                        op.remove(sub);
                        continue;
                    }
                    if (sup != -1) {
                        x = num.get(sup) + num.get(sup + 1);
                        num.set(sup, x);
                        num.remove(sup + 1);
                        op.remove(sup);
                        continue;
                    }
            }
            f2=1;
            numst = String.valueOf(num.get(0));
            num.clear();
            //System.out.println(numst);
            if (f1+f2==2){
                break;
            }
            if(havebk==1) {
                if (hbk==1){
                String smbk = "(" + bks.get(bks.size() - 1) + ")";
                String mbk = bks.get(bks.size()-2).replace(smbk, numst);
                bks.set(bks.size()-1, mbk);
                //System.out.println(bks.get(bks.size()-1)+" ()");
                bks.remove(bks.size() - 2);
                hbk=0;
                }
                else {
                if (bks.size() != 1 && hbk != 1) {
                    if (index == 1) {
                        bks.set(0, bks.get(0).replace(indexhub, numst));
                        bks.remove(bks.size() - 1);
                        index = 0;
                    } else {
                        String rbks = "(" + bks.get(bks.size() - 1) + ")";
                        //System.out.println(rbks + " )(");
                        bks.set(0, bks.get(0).replace(rbks, numst));
                        bks.remove(bks.size() - 1);
                    }
                }
                }
            }
            //System.out.println(bks.get(0)+" End");
        }
        System.out.print("="+numst);
    }
}
