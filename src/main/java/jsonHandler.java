import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class jsonHandler {
    public static String getOrgInfoViaIIN(String s) throws JSONException, NullPointerException {
       JSONObject jsonObject = null;
        if (s!=null){
            s = s.substring(1, s.length()-1);
        }

        try {

            assert s != null;
            jsonObject = new JSONObject(s);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        String status = jsonObject.getString("statusru");
        String datereg = jsonObject.getString("datereg");
        String nameru = jsonObject.getString("nameru");
        String director = jsonObject.getString("director");
        String addressru = jsonObject.getString("addressru");
        String bin = jsonObject.getString("bin");

        String organization = String.format("%s\nНаименование: %s\nСтатус: %s\nДата регистрации:%s\nРуководитель:%s\nЮр.адрес:%s",
                bin, nameru,status, datereg,director,addressru);
        return organization;
    }
}
