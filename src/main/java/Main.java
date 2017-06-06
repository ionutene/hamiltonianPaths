
import org.apache.commons.lang3.StringUtils;
import sun.util.resources.cldr.bs.CalendarData_bs_BA;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    Main() {
    }

    public static void main(String[] var0) throws InterruptedException, ConcurrentModificationException {

        CopyOnWriteArrayList as = new CopyOnWriteArrayList();
        LinkedHashSet<String> az = finalInit(init());
        for (String a : az) {
            as.add(a);
        }


        for (Object a : as) {
            if (a.toString().charAt(0) == 'a') {
                System.out.println("]");
                recursiveSolve(a, as);

                for (String p : az) {
                    as.add(p);
                }
            }
        }
/*
For the Set returned by finalInit(init()) we want to generate all possible hamiltonian paths
following next rules:
    - substrings of an selected string can not appear in next solutions:
        Ex: ACAB = {AC, CA, AB} will not belong to any of {BAJH - HGFE - EDCD - DEFG - GHJA} Strings
    - every solution will start the same character that previous solution ended:
        Ex: ACAB - BAJH  (B == B)
    - every path will start from point A and will finish in point A (it obvious from next examples)
Expected solutions
    ACAB - BAJH - HGFE - EDCD - DEFG - GHJA
    AJAB - BACD - DEFG - GHJH - HGFE - EDCA
    ABAC - CDED - DCAJ - JHGF - FEFG - GHJA
    AJAC - CDEF - FGHJ - JHGF - FEDC - CABA
    ACDC - CAJH - HGFE - EDEF - FGHJ - JABA*/

/* we got this
* [acab] - [bajh] - [hgfe] - [edcd] - [defg] - [ghja] - ] //OK
[ajab] - [bacd] - [dedc] - ] // will not explore further to get the real path throw "defg"
[abac] - [cded] - [dcaj] - [jhgf] - [fefg] - [ghja] - ] //OK
[ajac] - [cded] - [dcab] - ] //and so on...
[acdc] - [cajh] - [hgfe] - [edef] - [fghj] - [jaba] - ]
[acde] - [efed] - [dcab] - [baja] - ]
[ajhg] - [gfgh] - [hjab] - [baca] - ]
[abaj] - [jacd] - [dedc] - [jhgf] - [fefg] - ]
[acaj] - [jhgf] - [fedc] - ]
[ajhj] - [jacd] - [defg] - [ghgf] - [fedc] - [caba] -
* */

    }

    private static void recursiveSolve(Object input, CopyOnWriteArrayList result) throws InterruptedException, ConcurrentModificationException {
        if (result.size() > 0) {

            String in = input.toString();
            System.out.print("[" + input.toString() + "] - ");

            CopyOnWriteArrayList runFurther = result;
            runFurther = removeSubstrings(input, runFurther);
            for (Object walk : runFurther) {
                if (runFurther.size() > 0 &&
                        walk.toString().charAt(0) == in.charAt(3) &&
                        walk.toString().charAt(3) != in.charAt(0) &&
                        runFurther.contains(walk)) {

                    recursiveSolve(walk, runFurther);
                }
            }
        }

    }

    public static CopyOnWriteArrayList removeSubstrings(Object input, CopyOnWriteArrayList result) throws InterruptedException {
        ArrayList lis = new ArrayList();
        String a1 = input.toString().substring(0, 2);
        String a2 = input.toString().substring(1, 3);
        String a3 = input.toString().substring(2, 4);
        String i1 = "";
        String i2 = "";
        String i3 = "";

        for (Object in : result) {
//            String in = it.next();
            i1 = in.toString().substring(0, 2);
            i2 = in.toString().substring(1, 3);
            i3 = in.toString().substring(2, 4);
            if ((i1.equals(a1) || i1.equals(a2) || i1.equals(a3) ||
                    i2.equals(a1) || i2.equals(a2) || i2.equals(a3) ||
                    i3.equals(a1) || i3.equals(a2) || i3.equals(a3)
            )) {
                result.remove(in);
                lis.add(in);
            }
        }
/*        for (Object out : lis) {
            result.remove(out);
//            System.out.println("removed " + out);
        }*/

        return result;
    }

    public static LinkedHashSet<String> finalInit(LinkedHashSet<String> liste) throws ConcurrentModificationException {
        for (String a : liste) {
            if (a.substring(0, 2).equals(a.substring(2, 4))) {
                System.out.println(a.substring(2) + " = " + a.substring(2, 4));
                if (true) {
                    liste.remove(a);
                }
            }
        }
        return liste;
    }

    public static LinkedHashSet<String> init() {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher;
        LinkedHashSet<String> numberList = new LinkedHashSet<String>();
        String var5 = "";
        int var1 = 9;
        int var2 = var1;
        String[][] var10 = new String[var1][var2];

        String[][] myArrayA = new String[][]{
                { "0", "b", "c", "0", "0", "0", "0", "0", "j" },
                { "a", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "a", "0", "0", "d", "0", "0", "0", "0", "0" },
                { "0", "0", "c", "0", "e", "0", "0", "0", "0" },
                { "0", "0", "0", "d", "0", "f", "0", "0", "0" },
                { "0", "0", "0", "0", "e", "0", "g", "0", "0" },
                { "0", "0", "0", "0", "0", "f", "0", "h", "0" },
                { "0", "0", "0", "0", "0", "0", "g", "0", "j" },
                { "a", "0", "0", "0", "0", "0", "0", "h", "0" },
        };


        String[][] myArray = new String[][]{
                { "0", "ab", "ac", "0", "0", "0", "0", "0", "aj" },
                { "ba", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "ca", "0", "0", "cd", "0", "0", "0", "0", "0" },
                { "0", "0", "dc", "0", "de", "0", "0", "0", "0" },
                { "0", "0", "0", "ed", "0", "ef", "0", "0", "0" },
                { "0", "0", "0", "0", "fe", "0", "fg", "0", "0" },
                { "0", "0", "0", "0", "0", "gf", "0", "gh", "0" },
                { "0", "0", "0", "0", "0", "0", "hg", "0", "hj" },
                { "ja", "0", "0", "0", "0", "0", "0", "jh", "0" },
        };


        int var6;
        int var7;
        for (var6 = 0; var6 < var1; ++var6) {
            for (var7 = 0; var7 < var2; ++var7) {
                var10[var6][var7] = myArray[var6][var7];
            }
        }

        int var3 = var1;
        int var4 = var1;
        if (var2 != var3) {
        } else {
            String[][] var11 = new String[var3][var4];
            String[][] var12 = new String[var1][var4];
            String[][] var13 = new String[var1][var4];

            for (var6 = 0; var6 < var3; ++var6) {
                for (var7 = 0; var7 < var4; ++var7) {
                    var11[var6][var7] = myArrayA[var6][var7];
                }
            }

            for (var6 = 0; var6 < var1; ++var6) {
                for (var7 = 0; var7 < var4; ++var7) {
                    for (int var8 = 0; var8 < var3; ++var8) {
                        if (var10[var6][var8] != "0" && var11[var8][var7] != "0")
                            if (var12[var6][var7] == null) {
                                var5 += "(" + var10[var6][var8] + var11[var8][var7] + ")";
                            } else {
                                var5 += "-" + var12[var6][var7] + "__" + var10[var6][var8] + var11[var8][var7];
                            }
                    }

                    var12[var6][var7] = var5;
                    var5 = "";
                }
            }

            for (var6 = 0; var6 < var1; ++var6) {
                for (var7 = 0; var7 < var4; ++var7) {
                    for (int var8 = 0; var8 < var3; ++var8) {
                        if (var12[var6][var8] != "0" && var11[var8][var7] != "0") {

                            regexMatcher = regex.matcher(var12[var6][var8]);
                            while (regexMatcher.find()) {
                                matchList.add(regexMatcher.group(1));
                            }
                            for (String a : matchList) {

                                var5 += a + myArrayA[var8][var7] + "-";
                                for (String penis : var5.split("-")) {
                                    if (!penis.substring(0, 2).equals(penis.substring(2, 4))) {
                                        numberList.add(penis);
                                    } else continue;

                                }
                                if (!var5.substring(0, 2).equals(var5.substring(2, 4))) {
                                    numberList.add(StringUtils.substringBefore(var5, "-"));
                                } else continue;

                            }
                        }
                    }

                    var13[var6][var7] = var5;
                    var5 = "";
                    matchList.clear();
                }
            }

        }
        return numberList;
    }

}