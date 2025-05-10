package com.hutech.th_32;

import java.util.HashMap;

public class AccentedStringUtils {
    private static final HashMap<Character, String> ACCENTED_MAP = new HashMap<>();

    static {
        String[] accentedStrings = {
                "aáàãảạăắằẵẳặâấầẫẩậ",
                "AÁÀÃẢẠĂẮẰẴẲẶÂẤẦẪẨẬ",
                "dđ",
                "DĐ",
                "eéèẽẻẹêếềễểệ",
                "EÉÈẼẺẸÊẾỀỄỂỆ",
                "iíìĩỉị",
                "IÍÌĨỈỊ",
                "oóòõỏọôốồỗổộơớờỡởợ",
                "OÓÒÕỎỌÔỐỒỖỔỘƠỚỜỠỞỢ",
                "uúùũủụưứừữửự",
                "UÚÙŨỦỤƯỨỪỮỬỰ",
                "yýỳỹỷỵ",
                "YÝỲỸỶỴ"
        };

        for (String s : accentedStrings) {
            char key = s.charAt(0);
            ACCENTED_MAP.put(key, s.substring(1));
        }
    }

    public static String getAccentedString(char base) {
        return ACCENTED_MAP.get(base);
    }
}
