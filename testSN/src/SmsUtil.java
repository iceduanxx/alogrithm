/**
 * Created by IntelliJ IDEA.
 * User: lenovo
 * Date: 12-7-30
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public class SmsUtil {
    private static Client client = null;

    public static Client getSmsClient() throws Exception {
        if (client == null)
            client = new Client("DXX-BBX-010-12848", "749997");
        return client;
    }

    public static void main(String[] args) throws Exception {
        Client client = SmsUtil.getSmsClient();
        String result_mt = client.mt("15279156400", "花花你好", "", "", "");
        System.out.println(client.getBalance());
        System.out.println(client.mo());


    }
}