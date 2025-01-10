package ge.tbc.testautomation.dataproviders;

import org.testng.annotations.DataProvider;

public class SearchInputsDataProvider {
    @DataProvider(name = "searchInputData")
    public static Object[][] inputData() {
        return new Object[][]{
                {"ბათუმი"},
                {"კლუბი"},
                {"ალსკჯდაკლსდჯასდ"},
                {"ასლდჰასდ"},
        };
    }


    // this is for Conflict15

}
