package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void showMsg(String str) {
        String msg;
        String title = switch (str) {
            case "fill" -> {
                // Tüm alanları doldurun mesajı
                msg = "Please fill in all fields.";
                yield "Error!";
            }
            case "done" -> {
                // Başarılı mesajı
                msg = "Successful";
                yield "Result";
            }
            case "notFound" -> {
                // Bulunamadı mesajı
                msg = str + " Not found!";
                yield "Not found.";
            }

            default -> {
                msg = str;
                yield "Message";
            }
        };

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);


    }

    public static boolean confirm(String str){
        String msg;
        if(str.equals("Sure")){
            // İşlemi yapmak istediğinizden emin misiniz? mesajı
            msg = "Are you sure you want to do this action ?";
        }else{
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Are you sure ?",JOptionPane.YES_NO_OPTION) == 0 ;
    }


    // JTextField'ın boş olup olmadığını kontrol eden metot
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // JTextField dizisinin boş olup olmadığını kontrol eden metot
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    // Pencerenin konumunu ayarlayan metot
    public static int getLocationPoint(String type, Dimension size) {
        // Pencere tipine göre konum belirle
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

}