// Не знаю на сколько было уместно использовать Enum в данном случае, но хотелось закрепить на практике полученные знания.
public enum Roman {
    I(1, 73), V(5, 86), X(10, 88), L(50, 76), C(100, 67), D(500, 68), M(1000, 77);

    private int arabicNumber;
    private int uniCode;

    Roman (int arabicNumber, int uniCode){
        this.arabicNumber = arabicNumber;
        this.uniCode = uniCode;

    }

    public int getArabicNumber() {
        return arabicNumber;
    }
    public int getUniCode() {
        return uniCode;
    }

}
