package presentation;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements MainContract.View {

    private DefaultListModel coinModel;
    private JList coinList;
    private JScrollPane coinListScrollView;

    private JPanel coinInfoPanel;

    private JPanel coinHoldingsPanel;
    private JLabel coinHoldingsTitle;
    private JLabel coinHoldingsValue;

    private JPanel averagePurchasePricePanel;
    private JLabel averagePurchasePriceTitle;
    private JLabel averagePurchasePriceValue;

    private JPanel coinProfitPanel;
    private JLabel coinProfitTitle;
    private JLabel coinProfitValue;

    private JLabel dividingLine;

    private JPanel availableAssetsPanel;
    private JLabel availableAssetsTitle;
    private JLabel availableAssetsValue;

    private JPanel buyOrSellPanel;
    private JLabel buyOrSellTitle;
    private JTextField buyOrSellValue;

    private JButton buyButton;
    private JButton sellButton;

    private JTextArea noticeTextArea;
    private JScrollPane noticeScrollView;

    private JPanel sendCommandPanel;
    private JTextField sendCommandValue;
    private JButton sendCommandButton;

    private JPanel noticeTotalPanel;

    public View(String title) throws HeadlessException {
        super(title);
        setBounds(0, 0, 1000, 500);
        setLayout(new BorderLayout());

        setCoinInfoPanel();

        //TODO TEST UPDATE ALL COIN INFO
        for(int i = 0; i <= 100; ++i) {
            coinModel.addElement("test" + i);
        }

        setCoinHoldingsPanel();
        setAveragePurchasePricePanel();
        setCoinProfitPanel();
        dividingLine = new JLabel("---");
        coinInfoPanel.add(dividingLine);
        setAvailableAssetsPanel();
        setBuyOrSellPanel();
        add(coinInfoPanel, BorderLayout.WEST);
        setNoticePanel();

        add(noticeTotalPanel, BorderLayout.EAST);

        pack();
    }

    private void setCoinInfoPanel() {
        coinInfoPanel = new JPanel();
        coinInfoPanel.setLayout(new BoxLayout(coinInfoPanel, BoxLayout.Y_AXIS));

        coinModel = new DefaultListModel();
        coinList = new JList(coinModel);
        coinList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        coinListScrollView =new JScrollPane(coinList);
        coinListScrollView.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        coinInfoPanel.add(coinListScrollView, BorderLayout.NORTH);
    }

    private void setCoinHoldingsPanel() {
        coinHoldingsPanel = new JPanel();
        coinHoldingsPanel.setLayout(new FlowLayout());
        coinHoldingsTitle = new JLabel("보유량 :");
        coinHoldingsValue = new JLabel("0");

        coinHoldingsPanel.add(coinHoldingsTitle);
        coinHoldingsPanel.add(coinHoldingsValue);

        coinInfoPanel.add(coinHoldingsPanel);
    }

    private void setAveragePurchasePricePanel() {
        averagePurchasePricePanel = new JPanel();
        averagePurchasePricePanel.setLayout(new FlowLayout());
        averagePurchasePriceTitle = new JLabel("평균매수가 :");
        averagePurchasePriceValue = new JLabel("0");
        averagePurchasePricePanel.add(averagePurchasePriceTitle);
        averagePurchasePricePanel.add(averagePurchasePriceValue);

        coinInfoPanel.add(averagePurchasePricePanel);
    }

    private void setCoinProfitPanel() {
        coinProfitPanel = new JPanel();
        coinHoldingsPanel.setLayout(new FlowLayout());
        coinProfitTitle = new JLabel("수익 :");
        coinProfitValue = new JLabel("0");
        coinProfitPanel.add(coinProfitTitle);
        coinProfitPanel.add(coinProfitValue);

        coinInfoPanel.add(coinProfitPanel);
    }

    private void setAvailableAssetsPanel() {
        availableAssetsPanel = new JPanel();
        availableAssetsPanel.setLayout(new FlowLayout());
        availableAssetsTitle = new JLabel("가용 자산 :");
        availableAssetsValue = new JLabel("0");
        availableAssetsPanel.add(availableAssetsTitle);
        availableAssetsPanel.add(availableAssetsValue);

        coinInfoPanel.add(availableAssetsPanel);
    }

    private void setBuyOrSellPanel() {
        buyOrSellPanel = new JPanel();
        buyOrSellPanel.setLayout(new FlowLayout());
        buyOrSellTitle = new JLabel("매수/매도 수량 :");
        buyOrSellValue = new JTextField(10);

        buyButton = new JButton("매수");
        sellButton = new JButton("매도");

        buyOrSellPanel.add(buyOrSellTitle);
        buyOrSellPanel.add(buyOrSellValue);
        buyOrSellPanel.add(buyButton);
        buyOrSellPanel.add(sellButton);

        coinInfoPanel.add(buyOrSellPanel);
    }

    private void setNoticePanel() {
        noticeTextArea = new JTextArea();
        noticeScrollView = new JScrollPane(noticeTextArea);
        noticeScrollView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        noticeTextArea.setEditable(false);

        sendCommandPanel = new JPanel();
        sendCommandPanel.setLayout(new FlowLayout());
        sendCommandValue = new JTextField(15);
        sendCommandButton = new JButton("전송");
        sendCommandPanel.add(sendCommandValue);
        sendCommandPanel.add(sendCommandButton);

        noticeTotalPanel = new JPanel();
        noticeTotalPanel.setLayout(new BoxLayout(noticeTotalPanel, BoxLayout.Y_AXIS));
        noticeTotalPanel.add(noticeScrollView);
        noticeTotalPanel.add(sendCommandPanel);
    }

    @Override
    public void setAvailableAssets(String assets) {
        availableAssetsValue.setText(assets);
    }
}
