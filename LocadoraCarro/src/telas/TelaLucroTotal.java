package telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Locadora;

public class TelaLucroTotal {
    private Locadora locadora;

    public TelaLucroTotal(Locadora locadora){
    this.locadora = locadora;
    }

    public void mostrarLucroTotal() {
        JFrame popupFrame = new JFrame("Lucro Total");
        popupFrame.setSize(500, 200);
        popupFrame.setLocationRelativeTo(null);

        JLabel lucroLabel = new JLabel("Lucro Total: R$ " + String.format("%.2f", locadora.lucroTotal()), SwingConstants.CENTER);
        lucroLabel.setFont(new Font("Roboto", Font.BOLD, 20));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupFrame.setVisible(false);
            }
        });

        popupFrame.setLayout(new BorderLayout());
        popupFrame.add(lucroLabel, BorderLayout.CENTER);
        popupFrame.add(voltarButton, BorderLayout.SOUTH);

        popupFrame.setVisible(true);
    }
}



