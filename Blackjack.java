/*
Blackjack.java
Designed by Anthony Kervin

This file contains the main method for the Blackjack game, as well as the core logic.
*/

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Image;

public class Blackjack extends JFrame{ 
    // VARIABLES
    JButton btnExit,btnStart,btnBet,btnInsY,btnInsN,btnHit,btnStand,btnDoubleDown,btnNewBet,btnRestart;
    JLabel lblIntro,lblBank,lblBetAmount,lblIBetAmount,lblBet,lblBetMin,lblBetMax,lblHCard1,lblHCard2,lblHCard3,lblHCard4,lblHCard5,lblHCard6,lblHCard7,lblPCard1,lblPCard2,lblPCard3,lblPCard4,lblPCard5,lblPCard6,lblPCard7,lblInsQ,lblInsLoss,lblInsWin,lblPBust,lblHBust,lblWin,lblLoss,lblPush,lblBlackjack,lblGameOver;
    JTextField txtBet;

    double bank=100;
    double bet=0;
    double ibet=0;
    int hitCount=0;
    int endTurn=0;
    int cancelDoubleDown=0;
    int insurance=0;

    Player player=new Player("Player");
    Player house=new Player("House");
    Deck deck=new Deck();

    // CONSTRUCTOR
    private Blackjack(){
        super("Blackjack");
        setLayout(null);
        init();
        setSize(854,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(new Color(17,59,8));
    }

    // METHODS
    private void init(){        
        lblHCard1 = new JLabel();
        lblHCard1.setBounds(342,15,80,124);
        add(lblHCard1);

        lblHCard2 = new JLabel();
        lblHCard2.setBounds(432,15,80,124);
        add(lblHCard2);

        lblHCard3 = new JLabel();
        lblHCard3.setBounds(0,0,0,0);
        add(lblHCard3);

        lblHCard4 = new JLabel();
        lblHCard4.setBounds(0,0,0,0);
        add(lblHCard4);

        lblHCard5 = new JLabel();
        lblHCard5.setBounds(0,0,0,0);
        add(lblHCard5);

        lblHCard6 = new JLabel();
        lblHCard6.setBounds(0,0,0,0);
        add(lblHCard6);

        lblHCard7 = new JLabel();
        lblHCard7.setBounds(0,0,0,0);
        add(lblHCard7);

        lblPCard1 = new JLabel();
        lblPCard1.setBounds(342,266,80,124);
        add(lblPCard1);

        lblPCard2 = new JLabel();
        lblPCard2.setBounds(432,266,80,124);
        add(lblPCard2);

        lblPCard3 = new JLabel();
        lblPCard3.setBounds(0,0,0,0);
        add(lblPCard3);

        lblPCard4 = new JLabel();
        lblPCard4.setBounds(0,0,0,0);
        add(lblPCard4);

        lblPCard5 = new JLabel();
        lblPCard5.setBounds(0,0,0,0);
        add(lblPCard5);

        lblPCard6 = new JLabel();
        lblPCard6.setBounds(0,0,0,0);
        add(lblPCard6);

        lblPCard7 = new JLabel();
        lblPCard7.setBounds(0,0,0,0);
        add(lblPCard7);
        
        lblIntro= new JLabel("<html><p style='text-align:center'>Welcome to Blackjack, I hope you're feeling lucky!<br><br><span style='color:rgb(122,62,62)'>RULES:</span><br><span style='color:rgb(147,87,87)'>- Insurance pays 2:1 and = half your bet.</span><br><span style='color:rgb(172,112,112)'>- Blackjack pays 3:2.</span><br><span style='color:rgb(197,137,137)'>- No splitting hands.</span><br><span style='color:rgb(222,162,162)'>- Minimum bet is $5.</span><br><br><span style='color:green'>Press Start to play!</span><hr></p></html>");
        lblIntro.setBounds(252,90,350,250);
        lblIntro.setBackground(Color.BLACK);
        lblIntro.setForeground(Color.WHITE);
        lblIntro.setOpaque(true);
        add(lblIntro);

        lblBank=new JLabel("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
        lblBank.setBounds(0,0,0,0);
        lblBank.setForeground(Color.WHITE);
        add(lblBank);

        lblBetAmount=new JLabel("<html><p>Bet: <span style='color:yellow'>$"+String.valueOf(bet)+"</span></p></html>");
        lblBetAmount.setBounds(0,0,0,0);
        lblBetAmount.setForeground(Color.WHITE);
        add(lblBetAmount);

        lblIBetAmount=new JLabel("<html><p>Insurance Bet: <span style='color:yellow'>$"+String.valueOf(ibet)+"</span></p></html>");
        lblIBetAmount.setBounds(0,0,0,0);
        lblIBetAmount.setForeground(Color.WHITE);
        add(lblIBetAmount);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setBounds(784,420,60,30);
        btnExit.setBackground(new Color(170,1,20));
        btnExit.setForeground(Color.WHITE);
        add(btnExit);

        btnExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        // Start Button
        btnStart=new JButton("Start");
        btnStart.setBounds(377,360,100,50);
        btnStart.setBackground(new Color(47,89,38));
        btnStart.setForeground(Color.YELLOW);
        add(btnStart);

        btnStart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Bet();
            }
        });

        lblBet=new JLabel("<html><p style='text-align:center'>Please enter the bet amount (min $5):</p></html>");
        lblBet.setBounds(0,0,0,0);
        lblBet.setForeground(Color.WHITE);
        lblBet.setBackground(Color.BLACK);
        lblBet.setOpaque(true);
        add(lblBet);

        // Bet Amount TxtBox
        txtBet = new JTextField();
        txtBet.setBounds(0,0,0,0);
        txtBet.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtBet);

        // Button to Place bet
        btnBet=new JButton("<html><p style='text-align:center'>Place Bet<br>& Deal</p></html>");
        btnBet.setBounds(0,0,0,0);
        btnBet.setBackground(new Color(47,89,38));
        btnBet.setForeground(Color.YELLOW);
        add(btnBet);
        
        lblBetMin = new JLabel("<html><p style='text-align:center'>Your bet is too small. Minimum bet is $5.<br>Please try, again.</p></html>");
        lblBetMin.setBounds(0,0,0,0);
        lblBetMin.setForeground(Color.RED);
        add(lblBetMin);

        lblBetMax = new JLabel("<html><p style='text-align:center'>Your bet is larger than your bank account.<br>Please bet less.</p></html>");
        lblBetMax.setBounds(0,0,0,0);
        lblBetMax.setForeground(Color.RED);
        add(lblBetMax);

        lblInsQ = new JLabel("<html><p style='text-align:center'>Bet insurance?</p></html>");
        lblInsQ.setBounds(0,0,0,0);
        lblInsQ.setForeground(Color.WHITE);
        add(lblInsQ);

        // Button to Say Yes to Insurnace
        btnInsY=new JButton("Yes");
        btnInsY.setBounds(0,0,0,0);
        btnInsY.setBackground(new Color(47,89,38));
        btnInsY.setForeground(Color.YELLOW);
        add(btnInsY);

        // Button to Say No to Insurnace
        btnInsN=new JButton("No");
        btnInsN.setBounds(0,0,0,0);
        btnInsN.setBackground(new Color(47,89,38));
        btnInsN.setForeground(Color.YELLOW);
        add(btnInsN);

        lblInsWin=new JLabel("<html><p style='text-align:center'>Insurance Won! +$"+(2*ibet)+"</p></html>");
        lblInsWin.setBounds(0,0,0,0);
        lblInsWin.setForeground(Color.WHITE);
        add(lblInsWin);

        lblInsLoss=new JLabel("<html><p style='text-align:center'>Insurance Lost... -$"+(ibet)+"</p></html>");
        lblInsLoss.setBounds(0,0,0,0);
        lblInsLoss.setForeground(Color.WHITE);
        add(lblInsLoss);

        // Player Hit Loop Button
        btnHit=new JButton("Hit");
        btnHit.setBounds(0,0,0,0);
        btnHit.setBackground(new Color(47,89,38));
        btnHit.setForeground(Color.YELLOW);
        add(btnHit);
        btnHit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(player.cardsValue()<21 && endTurn==0){
                    player.drawCard(deck.dealCards());
                    hitCount++;
                    cancelDoubleDown=1;
                                    
                    switch(hitCount){
                        case 1: 
                            lblPCard1.setBounds(297,266,80,124);
                            lblPCard2.setBounds(387,266,80,124);
                            lblPCard3.setIcon(ShowCard(player,2));
                            lblPCard3.setBounds(477,266,80,124);
                            break;
                        case 2: 
                            lblPCard1.setBounds(252,266,80,124);
                            lblPCard2.setBounds(342,266,80,124);
                            lblPCard3.setBounds(432,266,80,124);
                            lblPCard4.setIcon(ShowCard(player,3));
                            lblPCard4.setBounds(522,266,80,124);
                            break;
                        case 3: 
                            lblPCard1.setBounds(207,266,80,124);
                            lblPCard2.setBounds(297,266,80,124);
                            lblPCard3.setBounds(387,266,80,124);
                            lblPCard4.setBounds(477,266,80,124);
                            lblPCard5.setIcon(ShowCard(player,4));
                            lblPCard5.setBounds(567,266,80,124);
                            break;
                        case 4: 
                            lblPCard1.setBounds(162,266,80,124);
                            lblPCard2.setBounds(252,266,80,124);
                            lblPCard3.setBounds(342,266,80,124);
                            lblPCard4.setBounds(432,266,80,124);
                            lblPCard5.setBounds(522,266,80,124);
                            lblPCard6.setIcon(ShowCard(player,5));
                            lblPCard6.setBounds(612,266,80,124);
                            break;
                        case 5: 
                            lblPCard1.setBounds(117,266,80,124);
                            lblPCard2.setBounds(207,266,80,124);
                            lblPCard3.setBounds(297,266,80,124);
                            lblPCard4.setBounds(387,266,80,124);
                            lblPCard5.setBounds(477,266,80,124);
                            lblPCard6.setBounds(567,266,80,124);
                            lblPCard7.setIcon(ShowCard(player,6));
                            lblPCard7.setBounds(657,266,80,124);
                            break; 
                    }
                    // Player Bust Loss
                    if(player.cardsValue()>21){
                        Insurance();
                        lblPBust.setText("<html><p style='text-align:center'>Bust! <span style='color:red'>-$"+String.valueOf(bet)+"</span></p></html>");
                        lblPBust.setBounds(377,198,100,20);
                        lblHCard2.setIcon(ShowCard(house,1));
                        bank-=bet;
                        lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                        endTurn++;
                        Restart();
                    }        
                } 
            }
        });

        // Player Stand Button
        btnStand=new JButton("Stand");
        btnStand.setBounds(0,0,0,0);
        btnStand.setBackground(new Color(47,89,38));
        btnStand.setForeground(Color.YELLOW);
        add(btnStand);
        btnStand.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(player.cardsValue()<=21 && endTurn==0){
                    endTurn++;
                    // HOUSE'S TURN
                    lblHCard2.setIcon(ShowCard(house,1));
                    // House Does not Have to Hit
                    if(house.cardsValue()>16){
                        // Player Win
                        if(player.cardsValue()>house.cardsValue()){
                            // Player BLACKJACK Win
                            if(player.cardsValue()==21){    
                                Insurance();
                                lblBlackjack.setText("<html><p style='text-align:center'>!!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(2.5*bet)+"</span></p></html>");
                                lblBlackjack.setBounds(340,198,175,20);
                                bank+=(bet*1.5);
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            }else{
                            // Player Normal Win
                                Insurance();
                                lblWin.setText("<html><p style='text-align:center'>You win! <span style='color:green'>+$"+String.valueOf(2*bet)+"</span></p></html>");
                                lblWin.setBounds(377,198,100,40);
                                bank+=bet;
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            }
                        // Player Loss
                        }else if(player.cardsValue()<house.cardsValue()){
                            Insurance();
                            lblLoss.setText("<html><p style='text-align:center'>You lose... <span style='color:red'>-$"+String.valueOf(bet)+"</span></p></html>");
                            lblLoss.setBounds(370,198,125,20);
                            bank-=bet;
                            lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                            Restart();
                        // Player Push
                        }else if(player.cardsValue()==house.cardsValue()){
                            Insurance();
                            lblPush.setText("<html><p style='text-align:center'>Push... <span style='color:yellow'>$"+String.valueOf(bet)+" returned.</span></p></html>");
                            lblPush.setBounds(352,198,150,40);
                            Restart();
                            lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                        }
                    // House has to Hit
                    }else{
                        Insurance();
                        hitCount=0;
                        while(house.cardsValue()<17 && house.cardsValue()<=21){
                            house.drawCard(deck.dealCards());
                            hitCount++;
                            switch(hitCount){
                                case 1: 
                                    lblHCard1.setBounds(297,15,80,124);
                                    lblHCard2.setBounds(387,15,80,124);
                                    lblHCard3.setIcon(ShowCard(house,2));
                                    lblHCard3.setBounds(477,15,80,124);
                                    break;
                                case 2: 
                                    lblHCard1.setBounds(252,15,80,124);
                                    lblHCard2.setBounds(342,15,80,124);
                                    lblHCard3.setBounds(432,15,80,124);
                                    lblHCard4.setIcon(ShowCard(house,3));
                                    lblHCard4.setBounds(522,15,80,124);
                                    break;
                                case 3: 
                                    lblHCard1.setBounds(207,15,80,124);
                                    lblHCard2.setBounds(297,15,80,124);
                                    lblHCard3.setBounds(387,15,80,124);
                                    lblHCard4.setBounds(477,15,80,124);
                                    lblHCard5.setIcon(ShowCard(house,4));
                                    lblHCard5.setBounds(567,15,80,124);
                                    break;
                                case 4: 
                                    lblHCard1.setBounds(162,15,80,124);
                                    lblHCard2.setBounds(252,15,80,124);
                                    lblHCard3.setBounds(342,15,80,124);
                                    lblHCard4.setBounds(432,15,80,124);
                                    lblHCard5.setBounds(522,15,80,124);
                                    lblHCard6.setIcon(ShowCard(house,5));
                                    lblHCard6.setBounds(612,15,80,124);
                                    break;
                                case 5: 
                                    lblHCard1.setBounds(117,15,80,124);
                                    lblHCard2.setBounds(207,15,80,124);
                                    lblHCard3.setBounds(297,15,80,124);
                                    lblHCard4.setBounds(387,15,80,124);
                                    lblHCard5.setBounds(477,15,80,124);
                                    lblHCard6.setBounds(567,15,80,124);
                                    lblHCard7.setIcon(ShowCard(house,6));
                                    lblHCard7.setBounds(657,15,80,124);
                                    break;
                            }
                        }
                        // House Bust, Player Win
                        if(house.cardsValue()>21){
                            // Player BLACKJACK Win
                            if(player.cardsValue()==21){    
                                lblBlackjack.setText("<html><p style='text-align:center'>House busts, !!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(2.5*bet)+"</span></p></html>");
                                lblBlackjack.setBounds(315,208,225,40);
                                bank+=(bet*1.5);
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            // Player Normal Win
                            }else{
                                lblHBust.setText("<html><p style='text-align:center'>House busts, you win! <span style='color:green'>+$"+String.valueOf(2*bet)+"</span></p></html>");
                                lblHBust.setBounds(315,208,225,40);
                                bank+=bet;
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            }
                        // House Stands, Player Win
                        }else if(player.cardsValue()>house.cardsValue()){
                            // Player BLACKJACK Win
                            if(player.cardsValue()==21){    
                                lblBlackjack.setText("<html><p style='text-align:center'>!!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(2.5*bet)+"</span></p></html>");
                                lblBlackjack.setBounds(340,198,175,20);
                                bank+=(bet*1.5);
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            // Player Normal Win
                            }else{
                                lblWin.setText("<html><p style='text-align:center'>You win! <span style='color:green'>+$"+String.valueOf(2*bet)+"</span></p></html>");
                                lblWin.setBounds(377,198,100,40);
                                bank+=bet;
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            }
                        // House Stands, Player Loss
                        }else if(player.cardsValue()<house.cardsValue()){
                            lblLoss.setText("<html><p style='text-align:center'>You lose... <span style='color:red'>-$"+String.valueOf(bet)+"</span></p></html>");
                            lblLoss.setBounds(370,198,125,20);
                            bank-=bet;
                            lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                            Restart();
                        // House Stands, Push
                        }else if(player.cardsValue()==house.cardsValue()){
                            lblPush.setText("<html><p style='text-align:center'>Push... <span style='color:yellow'>$"+String.valueOf(bet)+" returned.</span></p></html>");
                            lblPush.setBounds(352,198,150,40);
                            Restart();
                            lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                        }
                    }
                }
            }
        });

        // Player Double Down Button
        btnDoubleDown=new JButton("<html><p style='text-align:center'>Double<br>Down</p></html>");
        btnDoubleDown.setBounds(0,0,0,0);
        btnDoubleDown.setBackground(new Color(47,89,38));
        btnDoubleDown.setForeground(Color.YELLOW);
        add(btnDoubleDown);
        btnDoubleDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(player.cardsValue()<21 && endTurn==0 && cancelDoubleDown==0 && bank >= bet){
                    endTurn++;
                    cancelDoubleDown=1;
                    lblBetAmount.setText("<html><p>Bet: <span style='color:yellow'>$"+String.valueOf(2*bet)+"</span> - DOUBLED DOWN</p></html>");
                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank-ibet-(2*bet))+"</span></p></html>");
                    player.drawCard(deck.dealCards());
                    lblPCard1.setBounds(297,266,80,124);
                    lblPCard2.setBounds(387,266,80,124);
                    lblPCard3.setIcon(ShowCard(player,2));
                    lblPCard3.setBounds(477,266,80,124);
                    // Player Double Down Bust Loss
                    if(player.cardsValue()>21){
                        Insurance();
                        lblPBust.setText("<html><p style='text-align:center'>Bust! <span style='color:red'>-$"+String.valueOf(2*bet)+"</span></p></html>");
                        lblPBust.setBounds(377,198,100,20);
                        lblHCard2.setIcon(ShowCard(house,1));
                        bank-=(2*bet);
                        lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                        Restart();
                    }else{
                        // HOUSE'S TURN
                        lblHCard2.setIcon(ShowCard(house,1));
                        // House Does not Have to Hit
                        if(house.cardsValue()>16){
                            // Player Win
                            if(player.cardsValue()>house.cardsValue()){
                                // Player BLACKJACK Win
                                if(player.cardsValue()==21){    
                                    Insurance();
                                    lblBlackjack.setText("<html><p style='text-align:center'>!!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(((bet*2)+((bet*2)*1.5)))+"</span></p></html>");
                                    lblBlackjack.setBounds(340,198,175,20);
                                    bank+=(((bet*2)*1.5));
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                }else{
                                // Player Normal Win
                                    Insurance();
                                    lblWin.setText("<html><p style='text-align:center'>You win! <span style='color:green'>+$"+String.valueOf(4*bet)+"</span></p></html>");
                                    lblWin.setBounds(377,198,100,20);
                                    bank+=(2*bet);
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                }
                            // Player Loss
                            }else if(player.cardsValue()<house.cardsValue()){
                                Insurance();
                                lblLoss.setText("<html><p style='text-align:center'>You lose... <span style='color:red'>-$"+String.valueOf(2*bet)+"</span></p></html>");
                                lblLoss.setBounds(370,198,125,20);
                                bank-=(2*bet);
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            // Player Push
                            }else if(player.cardsValue()==house.cardsValue()){
                                Insurance();
                                lblPush.setText("<html><p style='text-align:center'>Push... <span style='color:yellow'>$"+String.valueOf(2*bet)+" returned.</span></p></html>");
                                lblPush.setBounds(352,198,150,40);
                                Restart();
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                            }
                        // House has to Hit
                        }else{
                            Insurance();
                            hitCount=0;
                            while(house.cardsValue()<17 && house.cardsValue()<=21){
                                house.drawCard(deck.dealCards());
                                hitCount++;
                                switch(hitCount){
                                    case 1: 
                                        lblHCard1.setBounds(297,15,80,124);
                                        lblHCard2.setBounds(387,15,80,124);
                                        lblHCard3.setIcon(ShowCard(house,2));
                                        lblHCard3.setBounds(477,15,80,124);
                                        break;
                                    case 2: 
                                        lblHCard1.setBounds(252,15,80,124);
                                        lblHCard2.setBounds(342,15,80,124);
                                        lblHCard3.setBounds(432,15,80,124);
                                        lblHCard4.setIcon(ShowCard(house,3));
                                        lblHCard4.setBounds(522,15,80,124);
                                        break;
                                    case 3: 
                                        lblHCard1.setBounds(207,15,80,124);
                                        lblHCard2.setBounds(297,15,80,124);
                                        lblHCard3.setBounds(387,15,80,124);
                                        lblHCard4.setBounds(477,15,80,124);
                                        lblHCard5.setIcon(ShowCard(house,4));
                                        lblHCard5.setBounds(567,15,80,124);
                                        break;
                                    case 4: 
                                        lblHCard1.setBounds(162,15,80,124);
                                        lblHCard2.setBounds(252,15,80,124);
                                        lblHCard3.setBounds(342,15,80,124);
                                        lblHCard4.setBounds(432,15,80,124);
                                        lblHCard5.setBounds(522,15,80,124);
                                        lblHCard6.setIcon(ShowCard(house,5));
                                        lblHCard6.setBounds(612,15,80,124);
                                        break;
                                    case 5: 
                                        lblHCard1.setBounds(117,15,80,124);
                                        lblHCard2.setBounds(207,15,80,124);
                                        lblHCard3.setBounds(297,15,80,124);
                                        lblHCard4.setBounds(387,15,80,124);
                                        lblHCard5.setBounds(477,15,80,124);
                                        lblHCard6.setBounds(567,15,80,124);
                                        lblHCard7.setIcon(ShowCard(house,6));
                                        lblHCard7.setBounds(657,15,80,124);
                                        break;
                                }
                            }
                            // House Bust, Player Win
                            if(house.cardsValue()>21){
                                // Player BLACKJACK Win
                                if(player.cardsValue()==21){    
                                    lblBlackjack.setText("<html><p style='text-align:center'>House busts, !!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(((bet*2)+((bet*2)*1.5)))+"</span></p></html>");
                                    lblBlackjack.setBounds(315,208,225,40);
                                    bank+=((2*bet)*1.5);
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                // Player Normal Win
                                }else{
                                    lblHBust.setText("<html><p style='text-align:center'>House busts, you win! <span style='color:green'>+$"+String.valueOf(4*bet)+"</span></p></html>");
                                    lblHBust.setBounds(315,208,225,40);
                                    bank+=(2*bet);
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                }
                            // House Stands, Player Win
                            }else if(player.cardsValue()>house.cardsValue()){
                                // Player BLACKJACK Win
                                if(player.cardsValue()==21){    
                                    lblBlackjack.setText("<html><p style='text-align:center'>!!!!BLACKJACK!!!! <span style='color:green'>+$"+String.valueOf(((bet*2)+((bet*2)*1.5)))+"</span></p></html>");
                                    lblBlackjack.setBounds(340,198,175,20);
                                    bank+=((2*bet)*1.5);
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                // Player Normal Win
                                }else{
                                    lblWin.setText("<html><p style='text-align:center'>You win! <span style='color:green'>+$"+String.valueOf(4*bet)+"</span></p></html>");
                                    lblWin.setBounds(377,198,100,40);
                                    bank+=(2*bet);
                                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                    Restart();
                                }
                            // House Stands, Player Loss
                            }else if(player.cardsValue()<house.cardsValue()){
                                lblLoss.setText("<html><p style='text-align:center'>You lose... <span style='color:red'>-$"+String.valueOf(2*bet)+"</span></p></html>");
                                lblLoss.setBounds(370,198,125,20);
                                bank-=(2*bet);
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                                Restart();
                            // House Stands, Push
                            }else if(player.cardsValue()==house.cardsValue()){
                                lblPush.setText("<html><p style='text-align:center'>Push... <span style='color:yellow'>$"+String.valueOf(2*bet)+" returned.</span></p></html>");
                                lblPush.setBounds(352,198,150,40);
                                Restart();
                                lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank)+"</span></p></html>");
                            }
                        }
                    }
                }
            }
        });

        lblPBust=new JLabel("<html><p style='text-align:center'>Bust! -$"+String.valueOf(bet)+"</p></html>");
        lblPBust.setBounds(0,0,0,0);
        lblPBust.setForeground(Color.WHITE);
        add(lblPBust);

        lblHBust=new JLabel("<html><p style='text-align:center'>House busts, you win! +$"+String.valueOf(2*bet)+"</p></html>");
        lblHBust.setBounds(0,0,0,0);
        lblHBust.setForeground(Color.WHITE);
        add(lblHBust);

        lblWin=new JLabel("<html><p style='text-align:center'>You win! +$"+String.valueOf(2*bet)+"</p></html>");
        lblWin.setBounds(0,0,0,0);
        lblWin.setForeground(Color.WHITE);
        add(lblWin);

        lblLoss=new JLabel("<html><p style='text-align:center'>You lose... -$"+String.valueOf(bet)+"</p></html>");
        lblLoss.setBounds(0,0,0,0);
        lblLoss.setForeground(Color.WHITE);
        add(lblLoss);

        lblPush=new JLabel("<html><p style='text-align:center'>Push... $"+String.valueOf(bet)+" returned.</p></html>");
        lblPush.setBounds(0,0,0,0);
        lblPush.setForeground(Color.WHITE);
        add(lblPush);

        lblBlackjack=new JLabel("<html><p style='text-align:center'>!!!!BLACKJACK!!!! +$"+String.valueOf(2.5*bet)+"</p></html>");
        lblBlackjack.setBounds(0,0,0,0);
        lblBlackjack.setForeground(Color.YELLOW);
        lblBlackjack.setBackground(Color.BLACK);
        lblBlackjack.setOpaque(true);
        add(lblBlackjack);

        // Button to Start a New Round with New Bet
        btnNewBet=new JButton("<html><p style='text-align:center'>Place Bet<br>& Deal</p></html>");
        btnNewBet.setBounds(0,0,0,0);
        btnNewBet.setBackground(new Color(47,89,38));
        btnNewBet.setForeground(Color.YELLOW);
        add(btnNewBet);

        lblGameOver=new JLabel("<html><p style='text-align:center'><span style='font-weight:bold'>GAME OVER. Bank ran dry...</span></p></html>");
        lblGameOver.setBounds(0,0,0,0);
        lblGameOver.setBackground(new Color(170,1,20));
        lblGameOver.setForeground(Color.RED);
        lblGameOver.setOpaque(true);
        add(lblGameOver);

        // Button to Restart Once Bank<5
        btnRestart=new JButton("Try Again");
        btnRestart.setBounds(0,0,0,0);
        btnRestart.setBackground(new Color(47,89,38));
        btnRestart.setForeground(Color.YELLOW);
        add(btnRestart);

        btnRestart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Blackjack blackjack = new Blackjack();
            }
        });
    }

    // Bet Page
    private void Bet(){
        
        lblIntro.setBounds(0,0,0,0);
        btnStart.setBounds(0,0,0,0);  

        lblBank.setBounds(10,160,100,20);
        
        lblBet.setBounds(327,150,200,40);
        txtBet.setBounds(377,200,100,30);        
        btnBet.setBounds(377,250,100,40);

        btnBet.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                lblBetMin.setBounds(0,0,0,0);
                lblBetMax.setBounds(0,0,0,0);
                bet=Integer.parseInt(txtBet.getText());
                if(bet>=5 && bet<=bank){
                    Game();
                }else if(bet<5){
                    lblBetMin.setBounds(327,50,200,50);
                }else if(bet>bank){
                    lblBetMax.setBounds(327,50,200,50);
                }
            }
        });
    }

    // Game Loop Page
    private void Game(){
        player.hand.clear();
        house.hand.clear();
        lblBet.setBounds(0,0,0,0);
        txtBet.setBounds(0,0,0,0);
        btnBet.setBounds(0,0,0,0);
        btnHit.setBounds(0,0,0,0);
        btnStand.setBounds(0,0,0,0);
        btnDoubleDown.setBounds(0,0,0,0);
        btnNewBet.setBounds(0,0,0,0);
        lblIBetAmount.setBounds(0,0,0,0);
        lblInsWin.setBounds(0,0,0,0);
        lblInsLoss.setBounds(0,0,0,0);
        lblBetMin.setBounds(0,0,0,0);
        lblBetMax.setBounds(0,0,0,0);
        lblHCard1.setBounds(342,15,80,124);
        lblHCard2.setBounds(432,15,80,124);
        lblHCard3.setBounds(0,0,0,0);
        lblHCard4.setBounds(0,0,0,0);
        lblHCard5.setBounds(0,0,0,0);
        lblHCard6.setBounds(0,0,0,0);
        lblPCard1.setBounds(342,266,80,124);
        lblPCard2.setBounds(432,266,80,124);
        lblPCard3.setBounds(0,0,0,0);
        lblPCard4.setBounds(0,0,0,0);
        lblPCard5.setBounds(0,0,0,0);
        lblPCard6.setBounds(0,0,0,0);
        lblPBust.setBounds(0,0,0,0);
        lblHBust.setBounds(0,0,0,0);
        lblWin.setBounds(0,0,0,0);
        lblLoss.setBounds(0,0,0,0);
        lblPush.setBounds(0,0,0,0);
        lblBlackjack.setBounds(0,0,0,0);

        lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank-bet)+"</span></p></html>");
        lblBetAmount.setText("<html><p>Bet: <span style='color:yellow'>$"+String.valueOf(bet)+"</span></p></html>");
        lblBetAmount.setBounds(10,185,200,20);

        endTurn=0;
        cancelDoubleDown=0;
        hitCount=0;
        deck=new Deck();
        deck.shuffleDeck();
        
        // INITAL DEAL
        for(int i=0;i<4;i++){

            // if even
            if(i%2==0){

            // drawCard() adds String "temp" (top card of deck) to ArrayList "hand" from dealCards()
                player.drawCard(deck.dealCards());

            // if Odd
            } else{
                house.drawCard(deck.dealCards());
            }
        }

        // Display Player's Cards
        lblPCard1.setIcon(ShowCard(player,0));
        lblPCard2.setIcon(ShowCard(player,1));
        // Display House's Cards
        lblHCard1.setIcon(ShowCard(house,0));
        lblHCard2.setIcon(BackCard());

        // Insurance Prompt
        if(house.hand.get(0).substring(1).equals("A") && ((bet*0.5)+bet)<=bank){
            lblInsQ.setBounds(372,159,150,40);
            btnInsY.setBounds(317,208,100,40);
            btnInsN.setBounds(437,208,100,40);

            // Yes to Insurance
            btnInsY.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    // PLAYER'S TURN
                    insurance=1;
                    ibet=(0.5*bet);
                    lblBank.setText("<html><p>Bank: <span style='color:yellow'>$"+String.valueOf(bank-ibet-bet)+"</span></p></html>");
                    lblIBetAmount.setText("<html><p>Insurance Bet: <span style='color:yellow'>$"+String.valueOf(ibet)+"</span></p></html>");
                    lblIBetAmount.setBounds(10,210,200,20);
            
                    lblInsQ.setBounds(0,0,0,0);
                    btnInsY.setBounds(0,0,0,0);
                    btnInsN.setBounds(0,0,0,0);
        
                    btnHit.setBounds(257,405,100,40);
                    btnStand.setBounds(377,405,100,40);
                    btnDoubleDown.setBounds(497,405,100,40);
                }
            });
            // No to Insurance
            btnInsN.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ibet=0;
                    insurance=0;
                    lblInsQ.setBounds(0,0,0,0);
                    btnInsY.setBounds(0,0,0,0);
                    btnInsN.setBounds(0,0,0,0);
                
                    btnHit.setBounds(257,405,100,40);
                    btnStand.setBounds(377,405,100,40);
                    btnDoubleDown.setBounds(497,405,100,40);
                }
            });
        // No Insurance Possible  
        }else{
            ibet=0;
            insurance=0;
            lblInsQ.setBounds(0,0,0,0);
            btnInsY.setBounds(0,0,0,0);
            btnInsN.setBounds(0,0,0,0);
        
            btnHit.setBounds(257,405,100,40);
            btnStand.setBounds(377,405,100,40);
            btnDoubleDown.setBounds(497,405,100,40);
        }        
    }

    // Show Player/House's Cards
    private ImageIcon ShowCard(Player s,int i){
        ImageIcon card = new ImageIcon("cards/"+s.getCardFromHand(i)+".jpg");
        Image image = card.getImage();
        Image newcard = image.getScaledInstance(80,124, java.awt.Image.SCALE_SMOOTH);
        card = new ImageIcon(newcard);
        return card;
    }

    // Show Back of Card
    private ImageIcon BackCard(){
        ImageIcon card = new ImageIcon("cards/back.png");
        Image image = card.getImage();
        Image newcard = image.getScaledInstance(80,124, java.awt.Image.SCALE_SMOOTH);
        card = new ImageIcon(newcard);
        return card;
    }
    // Insurance
    private void Insurance(){  
        if(insurance==1 && house.cardsValue()==21){
            lblInsWin.setBounds(352,165,200,30);
            lblInsWin.setText("<html><p style='text-align:center'>Insurance Won! <span style='color:green'>+$"+(ibet+(2*ibet))+"</span></p></html>");
            bank+=(2*ibet);
        }else if(insurance==1 && !(house.cardsValue()==21)){
            lblInsLoss.setBounds(340,165,200,30);
            lblInsLoss.setText("<html><p style='text-align:center'>Insurance Lost... <span style='color:red'>-$"+(ibet)+"</span></p></html>");
            bank-=ibet;
        }
    }
 
    // Start New Round
    private void NewRound(){
        lblBet.setBounds(679,163,160,40);
        txtBet.setBounds(679,213,60,40);
        btnNewBet.setBounds(739,213,100,40);
        btnNewBet.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                bet=Integer.parseInt(txtBet.getText());
                if(bet>=5 && bet<=bank){
                    Game();
                }else if(bet<5){
                    lblBetMax.setBounds(0,0,0,0);
                    lblBetMin.setBounds(534,178,150,60);
                }else if(bet>bank){
                    lblBetMin.setBounds(0,0,0,0);
                    lblBetMax.setBounds(534,178,150,60);
                }
            }
        });
    }

    // Method to Restart When Bank<5
    private void Restart(){
        if(bank<5){
            lblGameOver.setBounds(679,163,160,40);
            btnRestart.setBounds(679,213,160,40);
        }else{
            NewRound();
        }
    }

    // Main Method
    public static void main(String[] args){
        Blackjack blackjack = new Blackjack();
    }
}