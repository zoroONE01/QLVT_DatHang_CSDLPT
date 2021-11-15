/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MinhTo
 */
public class ValidationRegEx {

    private static final String SDTRegex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
    private static final String CMNDRegex = "(\\d{12}|\\d{9})*";
    private static final String bienSoRegex = "(?<vung>[1-9]\\d{1})[\\w]d{1}(\\d{4,5})";
    private static final String bienSoRegex2 = "[0-9]{2}[a-zA-Z]{1}[0-9]{4,7}$";
    private static final String textRegex = "^\\w+( +\\w+)*$";
    private static final String textAndNumRegex = "^[\\w\\d]+( +[\\w\\d]+)*$";
    private static final String textAndNumRegex2 = "([aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz0-9_AĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴA-Z]+\\s?)+";
    private static final String tiengVietRegex = "([aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz_AĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴAĂÂÁẮẤÀẰẦẢẲẨÃẴẪẠẶẬĐEÊÉẾÈỀẺỂẼỄẸỆIÍÌỈĨỊOÔƠÓỐỚÒỒỜỎỔỞÕỖỠỌỘỢUƯÚỨÙỪỦỬŨỮỤỰYÝỲỶỸỴA-Z]+\\s?)+";
    private static final String moneyFormatRegex = "(\\d)(?=(\\d{3})+(?!\\d))";
    //private static final String moneyRegex = "^[0-9]+(\\.\\d{3})?";
    private static final String moneyRegex = "^[0-9]{1,8}";
    private static final String numberRegex = "^[0-9]{1,3}";

//Kho
    public static final String maKho = "^[a-zA-Z0-9]{4}$";
    public static final String tenKho = "^[\\sa-zA-Z0-9_.,-/]{1,30}$";
    public static final String diaChi = "^[\\sa-zA-Z0-9_.,-/]{1,100}$";
    public static final String maCN = "^[a-zA-Z0-9_.-]{10}$";

    public static Pattern patternTiengVietRegex = Pattern.compile(tiengVietRegex);

    public static boolean valMaKho(String value) {
        Pattern patter = Pattern.compile(maKho);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valTenKho(String value) {
        Pattern patter = Pattern.compile(tenKho);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valDiaChi(String value) {
        Pattern patter = Pattern.compile(diaChi);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }
//============================================================    
//VatTu
    public static final String maVT = "^[\\sa-zA-Z0-9]{1,4}$";
    public static final String tenVT = "^[\\sa-zA-Z0-9_.,-/]{1,30}$";
    public static final String DVT = "^[\\sa-zA-Z0-9_.,-/]{1,15}$";
    public static final String donGia = "^[0-9,]{3,8}$";

    public static boolean valMaVT(String value) {
        Pattern patter = Pattern.compile(maVT);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valTenVT(String value) {
        Pattern patter = Pattern.compile(tenVT);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valDVT(String value) {
        Pattern patter = Pattern.compile(DVT);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valDonGia(String value) {
        Pattern patter = Pattern.compile(donGia);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }
//============================================================
//Don Dat Hang
    public static final String maDDH = "^[a-zA-Z0-9]{8}$";
    public static final String NCC = "^[\\sa-zA-Z0-9_.,-/]{1,100}$";
    public static final String kho = "^[\\[.*.\\]\\sa-zA-Z0-9_.,-/]{1,100}$";

    public static boolean valMaDDH(String value) {
        Pattern patter = Pattern.compile(maDDH);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

    public static boolean valKho(String value) {
        Pattern patter = Pattern.compile(kho);
        Matcher match = patter.matcher(removeAscent(value.replaceAll("\\[", "")));
        return match.matches();
    }

    public static boolean valNCC(String value) {
        Pattern patter = Pattern.compile(NCC);
        Matcher match = patter.matcher(removeAscent(value));
        return match.matches();
    }

//===================================================================
    public static boolean validationSDT(String sdt) {
        Pattern patter = Pattern.compile(SDTRegex);
        Matcher match = patter.matcher(sdt);
        return match.matches();
    }

    public static boolean validationCMND(String cmnd) {
        Pattern patter = Pattern.compile(CMNDRegex);
        Matcher match = patter.matcher(cmnd);
        return match.matches();
    }

    public static boolean validationBienSo(String bienso) {
        Pattern pattern = Pattern.compile(bienSoRegex2);
        Matcher matcher = pattern.matcher(bienso);
        return matcher.matches();
    }

    public static String layVungTuBanSo(String bienso) {
        Pattern pattern = Pattern.compile(bienSoRegex);
        Matcher matcher = pattern.matcher(bienso);
        matcher.find();
        return matcher.group("vung");
    }

    public static String laySoTuBienSo(String bienso) {
        Pattern pattern = Pattern.compile(bienSoRegex);
        Matcher matcher = pattern.matcher(bienso);
        matcher.find();
        return matcher.group(2);
    }

    public static boolean validationTextRegex(String text) {
        // kiểm tra đinh dạng text
//        String[] listText = text.split("\\s");
//        StringBuilder str = new StringBuilder();
//        for (String string : listText) {
//            if (!string.equals("")) {
//                str.append(string);
//                str.append(" ");
//            }
//        }
        Matcher matcher = patternTiengVietRegex.matcher(text);
        return matcher.matches();
    }

    public static String removeAscent(String str) {
        return str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a")
                .replaceAll("[èéẹẻẽêềếệểễ]", "e")
                .replaceAll("[ìíịỉĩ]", "i")
                .replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o")
                .replaceAll("[ùúụủũưừứựửữ]", "u")
                .replaceAll("[ỳýỵỷỹ]", "y")
                .replaceAll("[đ]", "d")
                .replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "A")
                .replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E")
                .replaceAll("[ÌÍỊỈĨ]", "I")
                .replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O")
                .replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "U")
                .replaceAll("[ỲÝỴỶỸ]", "Y")
                .replaceAll("[Đ]", "D");
    }

    public static String convertTiengVietFormat(String text) {
        String[] listText = text.split("\\s");
        StringBuilder str = new StringBuilder();
        for (String string : listText) {
            if (!string.equals("")) {
                str.append(string);
                str.append(" ");
            }
        }
        return str.toString().trim();
    }

    public static boolean validationTextAndNumRegex(String text) {
        Pattern pattern = Pattern.compile(textAndNumRegex2);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static String convertToMoneyFomart(String money) {
        if (money.length() <= 15) {
            Pattern pattern = Pattern.compile(moneyFormatRegex);
            Matcher matcher = pattern.matcher(money);
            //hạn mức 1 tỉ 1 món

            String st = null;
            st = matcher.replaceAll("$1,");

            return st;

        } else {
            return null;
        }

    }

    public static boolean validationMoneyRegex(String money) {
        Pattern pattern = Pattern.compile(moneyRegex);
        Matcher matcher = pattern.matcher(money);
        return matcher.matches();
    }

    public static boolean validationNumber(String num) {
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();

    }

    public static void main(String[] args) {
//        String bienso = "86-C1 21565";
//        System.out.println(validationBienSo(bienso));
//        String vung = layVungTuBanSo(bienso);
//        String so = laySoTuBienSo(bienso);
//        System.out.println("so " + so);
//        System.out.println("vung " + vung);

        String ten = "minhto123";
        System.out.println(validationTextAndNumRegex(ten));
//         
//          Pattern pattern = Pattern.compile(tiengVietRegex);
//        Matcher matcher = pattern.matcher(ten);
//        System.out.println(matcher.matches());
        String money = "2000000009";
//        System.out.println(money.indexOf('.'));
//        String r = money.substring(0, money.indexOf('.'));
//        System.out.println("rs" + r);
        String result = convertToMoneyFomart(money);
        System.out.println(result);
//        //đổi lại thành ban đầu
//        String rs = result.replaceAll(",", "");
//        System.out.println(rs);
//        System.out.println(convertToMoneyFomart(rs));

        String name = "Bui Minh Tơ      nguyễn thanh ttus";
        System.out.println(validationTextRegex(name));

    }

    public static String doubleToStringNoDecimal(double d) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        return formatter.format(d);
    }
}
