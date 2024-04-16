package core;

// Kombinasyon öğesi sınıfı, bir anahtar ve değer çiftini temsil eder
public class ComboItem {
    private int key;
    private String value;

    // Parametreli yapılandırıcı
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // Parametresiz yapılandırıcı
    public ComboItem(){

    }

    // Anahtar değerini döndüren metot
    public int getKey() {
        return key;
    }

    // Anahtar değerini ayarlayan metot
    public void setKey(int key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Nesne stringe dönüştürülürken değeri döndüren metot
    public String toString(){
        return this.value;
    }
}
