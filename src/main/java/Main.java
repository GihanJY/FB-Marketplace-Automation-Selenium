public class Main {
    public static void main(String[] args) {
        Facebook fb = new Facebook();

        fb.openFacebook();
        fb.loginFacebook();
        fb.navigateMarketplace();
        fb.searchMarketPlace();
        fb.logoutFacebook();
        fb.closeFacebook();
    }
}
