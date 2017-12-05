import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class TestGoogleSheetRead {


    @Test
    public void testUserCanReadTheGoogleSheet() throws IOException {


        //Build a new authorized API client service.
        Sheets service = ReadGoogleSheets.getSheetService();


        //Read the spreadsheet
        //https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit#gid=0

        String spreadSheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";

        String range = "Class Data!A2:E";

        ValueRange response = service.spreadsheets().values()
                .get(spreadSheetId,range)
                .execute();

        List<List<Object>> values = response.getValues();

        if(values == null || values.size() ==0){
            System.out.println("No data found.");
        }else {
            System.out.println("Name, Major");
            for(List row: values){
                //Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s, %s\n", row.get(0), row.get(4));
            }
        }

    }

}

