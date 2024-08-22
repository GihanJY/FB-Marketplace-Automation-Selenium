public class Main {
    public static void main(String[] args) {
        //Object to call methods in Facebook class.
        Facebook fb = new Facebook();

        //Call methods in a sequence order
        fb.openFacebook();
        fb.loginFacebook();
        fb.navigateMarketplace();
        fb.searchMarketPlace();
        fb.logoutFacebook();
        fb.closeFacebook();
    }
}
