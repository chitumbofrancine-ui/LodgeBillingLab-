package org.example.lodgebillinglab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LodgeBillingLab extends JFrame {
    final double PRICE_STANDARD = 1500, PRICE_DELUXE = 2500, PRICE_FAMILY = 5500, PRICE_VIP = 10000;
    JTextField txtName = new JTextField("John Phiri");
    JComboBox<String> cmbRoom = new JComboBox<>(new String[]{"Standard","Deluxe","Family","VIP"});
    JTextField txtNights = new JTextField("5");
    JTextField txtExtras = new JTextField("200");
    JTextField txtPaid = new JTextField("1000");
    JTextArea txtNotes = new JTextArea(3,20);
    JLabel lblStatus = new JLabel("");
    DefaultTableModel model;
    JTable table;
    int billId = 4;
    Color GOLD = new Color(212, 175, 55);
    Color BLACK = Color.BLACK;
    Color GOLD_LIGHT = new Color(255, 215, 0);

    public LodgeBillingLab(){
        setTitle("Lodge Billing Lab - VIP Edition");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // HEADER - BLACK
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(BLACK);
        top.setBorder(BorderFactory.createEmptyBorder(12,10,12,10));
        JLabel t = new JLabel("  Lodge Billing Lab");
        t.setForeground(GOLD_LIGHT);
        t.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(t, BorderLayout.WEST);
        add(top, BorderLayout.NORTH);

        // TOOLBAR - GOLD BUTTONS
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBackground(new Color(30,30,30));
        JButton bNew = new JButton("New guest");
        JButton bRec = new JButton("View receipt");
        JButton bDel = new JButton("Delete selected bill");
        // Make gold buttons
        for(JButton b : new JButton[]{bNew, bRec, bDel}){
            b.setBackground(GOLD);
            b.setForeground(BLACK);
            b.setFont(new Font("Arial", Font.BOLD, 13));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8,15,8,15));
        }
        toolbar.add(bNew); toolbar.add(bRec); toolbar.add(bDel);

        // FORM - Center
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8,8,8,8); g.fill=GridBagConstraints.HORIZONTAL;
        g.gridx=0; g.gridy=0; g.gridwidth=2;
        JLabel h1=new JLabel("Lodge Billing Lab");
        h1.setFont(new Font("Arial", Font.BOLD,24));
        h1.setForeground(BLACK);
        form.add(h1,g);
        g.gridwidth=1;
        // REMOVED ICT261 text as you requested

        g.gridy=1; g.gridx=0; form.add(new JLabel("Guest name:"),g); g.gridx=1; form.add(txtName,g);
        g.gridy=2; g.gridx=0; form.add(new JLabel("Room type:"),g); g.gridx=1; cmbRoom.setSelectedIndex(1); form.add(cmbRoom,g);
        g.gridy=3; g.gridx=0; form.add(new JLabel("Nights:"),g); g.gridx=1; form.add(txtNights,g);
        g.gridy=4; g.gridx=0; form.add(new JLabel("Extras in K:"),g); g.gridx=1; form.add(txtExtras,g);
        g.gridy=5; g.gridx=0; form.add(new JLabel("Amount paid in K:"),g); g.gridx=1; form.add(txtPaid,g);
        g.gridy=6; g.gridx=0; form.add(new JLabel("Notes:"),g); g.gridx=1; form.add(new JScrollPane(txtNotes),g);

        // BIGGER BUTTONS WITH ICONS
        g.gridy=7; g.gridx=0;
        JButton btnCalc=new JButton("🧮 Calculate");
        btnCalc.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalc.setBackground(BLACK);
        btnCalc.setForeground(GOLD_LIGHT);
        btnCalc.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        form.add(btnCalc,g);

        g.gridx=1;
        JButton btnSave=new JButton("💾 Save bill");
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        btnSave.setBackground(GOLD);
        btnSave.setForeground(BLACK);
        btnSave.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        form.add(btnSave,g);

        // RIGHT PANEL - BLACK AND GOLD
        JPanel right=new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(new Color(20,20,20));
        right.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JLabel rpTitle = new JLabel("<html><b style='color:#FFD700;'>Practice room prices</b></html>");
        rpTitle.setFont(new Font("Arial", Font.BOLD, 14));
        right.add(rpTitle);
        right.add(Box.createVerticalStrut(10));
        JLabel l1 = new JLabel("Standard: K 1500.00 per night"); l1.setForeground(Color.WHITE);
        JLabel l2 = new JLabel("Deluxe: K 2500.00 per night"); l2.setForeground(Color.WHITE);
        JLabel l3 = new JLabel("Family: K 5500.00 per night"); l3.setForeground(Color.WHITE);
        JLabel l4 = new JLabel("VIP: K 10000.00 per night 👑"); l4.setForeground(GOLD_LIGHT); l4.setFont(new Font("Arial", Font.BOLD, 13));
        right.add(l1); right.add(l2); right.add(l3); right.add(l4);
        right.add(new JLabel(" "));
        JLabel info = new JLabel("<html><div style='color:white;'>7 nights or more: 10% off<br>Extras no discount. No tax.<br><br>👑 VIP guests get a crown<br>beside their name.<br><br>Saved bills last only until<br>you close the app.</div></html>");
        right.add(info);

        JPanel center=new JPanel(new BorderLayout());
        center.add(form, BorderLayout.CENTER);
        center.add(right, BorderLayout.EAST);

        // BOTTOM TABLE
        JPanel bottom=new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        JPanel bh=new JPanel(new FlowLayout(FlowLayout.LEFT));
        bh.setBackground(Color.WHITE);
        JLabel s=new JLabel("Saved bills    ");
        s.setFont(new Font("Arial", Font.BOLD,18));
        s.setForeground(BLACK);
        bh.add(s); bh.add(lblStatus);
        bottom.add(bh, BorderLayout.NORTH);
        model=new DefaultTableModel(new String[]{"Bill","Guest","Room","Nights","Total","Paid","Balance"},0);
        table=new JTable(model);
        // Existing sample data with new prices
        model.addRow(new Object[]{"B002","Mary Banda","Standard","2","K 3200.00","K 500.00","K 2700.00"});
        model.addRow(new Object[]{"B003","👑 John Phiri","VIP","5","K 50200.00","K 1000.00","K 49200.00"});
        lblStatus.setText("Saved B003: Balance: K 49200.00 - VIP 👑");
        lblStatus.setForeground(new Color(0,128,0));
        lblStatus.setFont(new Font("Arial", Font.BOLD, 13));
        bottom.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel main=new JPanel(new BorderLayout());
        main.add(toolbar, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);

        // LOGIC - 7 nights 10% off + VIP crown
        btnCalc.addActionListener(e->{
            try{
                double tot=calcTotal();
                double paid=getDouble(txtPaid);
                String crown = cmbRoom.getSelectedItem().equals("VIP") ? "👑 " : "";
                JOptionPane.showMessageDialog(this,crown + "Total: K "+tot+"\nBalance: K "+(tot-paid));
            }catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage());}
        });

        btnSave.addActionListener(e->{
            try{
                double tot=calcTotal();
                double paid=getDouble(txtPaid);
                double bal=tot-paid;
                String id=String.format("B%03d",billId++);
                boolean isVip = cmbRoom.getSelectedItem().equals("VIP");
                String guestName = txtName.getText();
                if(isVip && !guestName.startsWith("👑")){
                    guestName = "👑 " + guestName; // Add crown
                }
                model.addRow(new Object[]{id,guestName,cmbRoom.getSelectedItem(),txtNights.getText(),String.format("K %.2f",tot),String.format("K %.2f",paid),String.format("K %.2f",bal)});
                lblStatus.setText("Saved "+id+": Balance: K "+String.format("%.2f",bal) + (isVip ? " - VIP 👑" : ""));
            }catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage());}
        });

        bNew.addActionListener(e->{txtName.setText(""); txtNights.setText(""); txtExtras.setText(""); txtPaid.setText(""); txtNotes.setText(""); cmbRoom.setSelectedIndex(0);});
        bDel.addActionListener(e->{int r=table.getSelectedRow(); if(r!=-1) model.removeRow(r);});
        bRec.addActionListener(e->{int r=table.getSelectedRow(); if(r!=-1) JOptionPane.showMessageDialog(this,"RECEIPT\nBill: "+model.getValueAt(r,0)+"\nGuest: "+model.getValueAt(r,1)+"\nTotal: "+model.getValueAt(r,4)+"\nBalance: "+model.getValueAt(r,6));});

        setVisible(true);
    }

    double getDouble(JTextField t){return t.getText().trim().isEmpty()?0:Double.parseDouble(t.getText().trim());}

    double calcTotal() throws Exception{
        int nights=Integer.parseInt(txtNights.getText().trim());
        if(nights<=0) throw new Exception("Nights >0");
        double price=PRICE_STANDARD;
        String room = cmbRoom.getSelectedItem().toString();
        if(room.equals("Deluxe")) price=PRICE_DELUXE;
        if(room.equals("Family")) price=PRICE_FAMILY;
        if(room.equals("VIP")) price=PRICE_VIP;
        double charge=price*nights;
        if(nights>=7) charge*=0.90; // 10% off for 7+ nights
        return charge+getDouble(txtExtras);
    }

    public static void main(String[] args){SwingUtilities.invokeLater(()-> new LodgeBillingLab());}
}
