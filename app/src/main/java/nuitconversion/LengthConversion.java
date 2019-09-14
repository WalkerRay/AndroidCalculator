package nuitconversion;

public class LengthConversion {
    public String mmTocm(String mm) {
        double MM = Double.parseDouble(mm);
        double CM = MM * 0.1;
        return String.format("%.5f",CM);
    }

    public String cmTom(String cm) {
        double CM = Double.parseDouble(cm);
        double M = CM * 0.01;
        return String.format("%.5f",M);
    }

    public String mTokm(String m) {
        double M = Double.parseDouble(m);
        double KM = M * 0.001;
        return String.format("%.5f",KM);
    }

    public String kmTom(String km) {
        double KM = Double.parseDouble(km);
        double M = KM * 1000;
        return String.format("%.5f",M);
    }

    public String mTocm(String m) {
        double M = Double.parseDouble(m);
        double CM = M * 100;
        return String.format("%.5f",CM);
    }

    public String cmTomm(String cm) {
        double CM = Double.parseDouble(cm);
        double MM = CM * 10;
        return String.format("%.5f",MM);
    }

    public String mmTom(String mm){
        return cmTom(mmTocm(mm));
    }
    public String mmTokm(String mm){
        return mTokm(cmTom(mmTocm(mm)));
    }
    public String cmTokm(String cm){
        return mTokm(cmTom(cm));
    }
    public String kmTocm(String km){
        return mTocm(kmTom(km));
    }
    public String mTomm(String m){
        return cmTomm(mTocm(m));
    }
    public String kmTomm(String km){
        return cmTomm(kmTocm(km));
    }
}
