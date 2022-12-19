package presentation;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private DefaultListModel model;
    private JList coinList;
    private JScrollPane coinListScrollView;

    public View(String title) throws HeadlessException {
        super(title);
        setBounds(0, 0, 1000, 500);
        setLayout(new BorderLayout());

        JPanel coinInfoPanel = new JPanel();
        coinInfoPanel.setLayout(new BoxLayout(coinInfoPanel, BoxLayout.Y_AXIS));

        model = new DefaultListModel();
        coinList = new JList(model);
        coinList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        coinListScrollView =new JScrollPane(coinList);
        coinListScrollView.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        coinInfoPanel.add(coinListScrollView, BorderLayout.NORTH);

        //TODO TEST UPDATE ALL COIN INFO
        for(int i = 0; i <= 100; ++i) {
            model.addElement("test" + i);
        }

        JPanel coinHoldingsPanel = new JPanel();
        coinHoldingsPanel.setLayout(new FlowLayout());
        JLabel coinHoldingsTitle = new JLabel("보유량 :");
        JLabel coinHoldingsValue = new JLabel("0");

        coinHoldingsPanel.add(coinHoldingsTitle);
        coinHoldingsPanel.add(coinHoldingsValue);

        coinInfoPanel.add(coinHoldingsPanel);

        JPanel averagePurchasePricePanel = new JPanel();
        coinHoldingsPanel.setLayout(new FlowLayout());
        JLabel averagePurchasePriceTitle = new JLabel("평균매수가 :");
        JLabel averagePurchasePriceValue = new JLabel("0");
        averagePurchasePricePanel.add(averagePurchasePriceTitle);
        averagePurchasePricePanel.add(averagePurchasePriceValue);

        coinInfoPanel.add(averagePurchasePricePanel);

        JPanel coinProfitPanel = new JPanel();
        coinHoldingsPanel.setLayout(new FlowLayout());
        JLabel coinProfitTitle = new JLabel("수익 :");
        JLabel coinProfitValue = new JLabel("0");
        coinProfitPanel.add(coinProfitTitle);
        coinProfitPanel.add(coinProfitValue);

        coinInfoPanel.add(coinProfitPanel);

        JLabel dividingLine = new JLabel("---");
        coinInfoPanel.add(dividingLine);

        JPanel availableAssetsPanel = new JPanel();
        availableAssetsPanel.setLayout(new FlowLayout());
        JLabel availableAssetsTitle = new JLabel("가용 자산 :");
        JLabel availableAssetsValue = new JLabel("0");
        availableAssetsPanel.add(availableAssetsTitle);
        availableAssetsPanel.add(availableAssetsValue);

        coinInfoPanel.add(availableAssetsPanel);

        JPanel buyOrSellPanel = new JPanel();
        buyOrSellPanel.setLayout(new FlowLayout());
        JLabel buyOrSellTitle = new JLabel("매수/매도 수량 :");
        JTextField buyOrSellValue = new JTextField(10);

        JButton buyButton = new JButton("매수");
        JButton sellButton = new JButton("매도");

        buyOrSellPanel.add(buyOrSellTitle);
        buyOrSellPanel.add(buyOrSellValue);
        buyOrSellPanel.add(buyButton);
        buyOrSellPanel.add(sellButton);

        coinInfoPanel.add(buyOrSellPanel);

        add(coinInfoPanel, BorderLayout.WEST);

        JTextArea noticeTextArea = new JTextArea();
        JScrollPane noticeScrollView = new JScrollPane(noticeTextArea);
        noticeScrollView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        noticeTextArea.setEditable(false);

        JPanel sendCommandPanel = new JPanel();
        sendCommandPanel.setLayout(new FlowLayout());
        JTextField sendCommandValue = new JTextField(15);
        JButton sendCommandButton = new JButton("전송");
        sendCommandPanel.add(sendCommandValue);
        sendCommandPanel.add(sendCommandButton);

        JPanel noticeTotalPanel = new JPanel();
        noticeTotalPanel.setLayout(new BoxLayout(noticeTotalPanel, BoxLayout.Y_AXIS));
        noticeTotalPanel.add(noticeScrollView);
        noticeTotalPanel.add(sendCommandPanel);

        add(noticeTotalPanel, BorderLayout.EAST);

        pack();
    }
}
