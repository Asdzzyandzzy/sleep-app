package ui;

public class Choose {
    public Choose() {

    }

    //EFFECTS: to check user type in and run right method
    public void chooseThings(String s) {

        if (s.equals("r")) {
            Recordsleep r;
            r = new Recordsleep();
            r.recordd();
        }



    }







    //EFFECTS: read reader type to ready to choose things
    public void chooseThing(String s) {

        System.out.println("YESS");
        chooseThings(s);
     ///   chooseThingss(s);
        if (s.equals("q")) {
       //     keepGoing = false;
            System.out.println("The app is quit!");
        }
    }
}
