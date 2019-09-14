package nuitconversion;

public class VolumeConversion {

    public String mlTol(String ml){
        double ML = Double.parseDouble(ml);
        double L = ML * 0.001;
        return String.format("%.5f",L);
    }

    public String mlTom3(String ml){
        double ML = Double.parseDouble(ml);
        double M = ML * 0.000001;
        return String.format("%.5f",M);
    }

    public String lToml(String l){
        double L = Double.parseDouble(l);
        double ML = L * 1000;
        return String.format("%.5f",ML);
    }

    public String lTocm3(String l){
        double L = Double.parseDouble(l);
        double CM = L * 1000;
        return String.format("%.5f",CM);
    }

    public String lTom3(String l){
        double L = Double.parseDouble(l);
        double M = L * 0.001;
        return String.format("%.5f",M);
    }

    public String cm3Tol(String cm){
        return mlTol(cm);
    }
    public String cm3Tom3(String cm){
        return mlTom3(cm);
    }

    public String m3Toml(String m){
        double M = Double.parseDouble(m);
        double ML = M * 1000000;
        return String.format("%.5f",ML);
    }
    public String m3Tol(String m){
        double M = Double.parseDouble(m);
        double L = M * 1000;
        return String.format("%.5f",L);
    }
    public String m3Tocm3(String m){
        return m3Toml(m);
    }

}
