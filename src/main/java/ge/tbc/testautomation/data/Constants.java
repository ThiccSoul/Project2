package ge.tbc.testautomation.data;

public class Constants {
    public static final String
            mainURL = "https://api.swoop.ge/api/search?filter[keyword]=კახეთი&LangID=1",
            apiKey1 = "filter[keyword]",
            apiKey2 = "LangID",
            apiKey2Value = "1",
            searchErrText = "შეთავაზება არ მოიძებნა",
            engLangErrText = "Language is not English",
            geoLangErrText = "Language is not Georgian",
            standardUserSQL = "SELECT username, password FROM Users WHERE username = 'standard_user'",
            lockedUserSQL = "SELECT username, password FROM Users WHERE username = 'locked_out_user'";

    // this is for Conflict123
}
