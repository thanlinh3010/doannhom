package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class BoardClient extends javax.swing.JFrame {
    static String tenNguoiChoi1;
    static Integer idNguoiChoi1;
    static Integer idNguoiChoi2;
    static String tenNguoiChoi2;
    final int COLUMN = 19, ROW = 19;
    JButton btnOnBoard[][];
    JButton lastMove = null;
    int[][] boardState = new int[ROW][COLUMN];
    public BoardClient() {
        initComponents();
        int[][] boardState = new int[ROW][COLUMN];
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // board
        plBoardContainer.setLayout(new GridLayout(ROW, COLUMN));
        initBoard();
        String[] options = {"Người chơi 1", "Người chơi 2"};
         int result = JOptionPane.showOptionDialog(this, "Chọn người chơi đi trước", "Bắt đầu trận đấu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (result == JOptionPane.YES_OPTION) {
        currentPlayer = 1;
    } else {
        currentPlayer = 2;
    
    }  
    }
    
    public void initBoard() {
        plBoardContainer.removeAll();
        btnOnBoard = new JButton[COLUMN + 2][ROW + 2];

        for (int row = 0; row < ROW; row++) {
            for (int column = 0; column < COLUMN; column++) {
                btnOnBoard[row][column] = createBoardButton(row, column);
                plBoardContainer.add(btnOnBoard[row][column]);
            }
        }
    }
    public void setLastMove(int row, int column) {
        lastMove = btnOnBoard[row][column];
    }
    public void addPoint(int row, int column) {
        if (lastMove != null) {
            lastMove.setBackground(new Color(180, 180, 180));
        }

        lastMove = btnOnBoard[row][column];
        lastMove.setBackground(Color.yellow);
    }
  
    private int currentPlayer = 1;
    private ImageIcon xIcon = new ImageIcon("src/IMG/X1.png");
    private ImageIcon oIcon = new ImageIcon("src/IMG/O1.png");
    public JButton createBoardButton(int row, int column) {
    JButton b = new JButton();
    b.setFocusPainted(false);
    b.setActionCommand("");
    b.setPreferredSize(new Dimension(26,26));
    Dimension defaultSize = b.getPreferredSize();
    b.setPreferredSize(defaultSize);
    b.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy nút đã được nhấp
            JButton button = (JButton) e.getSource();
            
            // Kiểm tra xem nút đã được nhấp chưa
            if (!button.getActionCommand().equals("")) {
                return;
            }
            
            // Đặt lệnh hành động của nút để đánh dấu nút đãđược nhấp
            button.setActionCommand(Integer.toString(currentPlayer));
            
            // Đặt màu nền cho nút dựa trên người chơi hiện tại
            if (currentPlayer == 1) {
                button.setIcon(xIcon);
            } else {
                button.setIcon(oIcon);
            }
            
            // Chuyển sang người chơi khác
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
   

    });
    
    
    return b;
}
  public void handleClickButton(int row, int column) {
  if (!btnOnBoard[row][column].getActionCommand().equals("")) {
     return; 
  }
  
  if (currentPlayer == 1) {
     boardState[row][column] = 1; 
  } else {
     boardState[row][column] = 2;    
  }
  
  // Cập nhật giao diện.....
    
  boolean won = checkWin(row, column);
  if (won) {
     // Hiển thị người thắng ... 
  }
}
    public boolean checkWin(int row, int column) {
    int count = 1;
    int player = boardState[row][column];
    // Kiểm tra hàng ngang
    for (int i = column - 1; i >= 0; i--) {
        if (btnOnBoard[row + 1][i + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    for (int i = column + 1; i < COLUMN + 2; i++) {
        if (btnOnBoard[row + 1][i + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    if (count >= 5) {
        return true;
    }

    // Kiểm tra hàng dọc
    count = 1;
    for (int i = row - 1; i >= 0; i--) {
        if (btnOnBoard[i + 1][column + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    for (int i = row + 1; i < ROW + 2; i++) {
        if (btnOnBoard[i + 1][column + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    if (count >= 5) {
        return true;
    }

    // Kiểm tra đường chéo chính (\)
    count = 1;
    for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
        if (btnOnBoard[i + 1][j + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    for (int i = row + 1, j = column + 1; i < ROW + 2 && j < COLUMN + 2; i++, j++) {
       if (btnOnBoard[i + 1][j + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    if (count >= 5) {
        return true;
    }

    // Kiểm tra đường chéo phụ (/)
    count = 1;
    for (int i = row - 1, j = column + 1; i >= 0 && j < COLUMN + 2; i--, j++) {
        if (btnOnBoard[i + 1][j + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    for (int i = row + 1, j = column - 1; i < ROW + 2 && j >= 0; i++, j--) {
        if (btnOnBoard[i + 1][j + 1].getActionCommand().equals(Integer.toString(player))) {
            count++;
        } else {
            break;
        }
    }
    if (count >= 5) {
        return true;
    }

    return false;
}

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plPlayer = new javax.swing.JPanel();
        lbAvartar2 = new javax.swing.JLabel();
        lbAvartar1 = new javax.swing.JLabel();
        lbTen1 = new javax.swing.JLabel();
        lbTen2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        plBoardContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Caro");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);

        plPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Người chơi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lbAvartar2.setBackground(new java.awt.Color(255, 153, 153));
        lbAvartar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/2 (1).png"))); // NOI18N

        lbAvartar1.setBackground(new java.awt.Color(255, 153, 153));
        lbAvartar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/1 (1).png"))); // NOI18N

        lbTen1.setText("Bình");

        lbTen2.setText("Linh");

        javax.swing.GroupLayout plPlayerLayout = new javax.swing.GroupLayout(plPlayer);
        plPlayer.setLayout(plPlayerLayout);
        plPlayerLayout.setHorizontalGroup(
            plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAvartar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAvartar2)
                .addContainerGap())
            .addGroup(plPlayerLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lbTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTen2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        plPlayerLayout.setVerticalGroup(
            plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAvartar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAvartar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTen1)
                    .addComponent(lbTen2))
                .addGap(42, 42, 42))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        jButton3.setText("Ván mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Thoát");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout plBoardContainerLayout = new javax.swing.GroupLayout(plBoardContainer);
        plBoardContainer.setLayout(plBoardContainerLayout);
        plBoardContainerLayout.setHorizontalGroup(
            plBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );
        plBoardContainerLayout.setVerticalGroup(
            plBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plBoardContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(plBoardContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
            Home myHome = new Home();
            myHome.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     reload();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    private void reload() {
    // đóng và mở lại JFrame BoardAI để chạy lại
    dispose();
    BoardAI board = new BoardAI();
    board.setVisible(true);
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardClient().setVisible(true);
            
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAvartar1;
    private javax.swing.JLabel lbAvartar2;
    private javax.swing.JLabel lbTen1;
    private javax.swing.JLabel lbTen2;
    private javax.swing.JPanel plBoardContainer;
    private javax.swing.JPanel plPlayer;
    // End of variables declaration//GEN-END:variables

    void setNguoiChoi(String tenNguoiChoi1, int id1Int, String tenNguoiChoi2, int id2Int) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
