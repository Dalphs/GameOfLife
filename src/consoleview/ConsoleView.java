package consoleview;

import model.Game;
import viewmodel.ViewModel;

public class ConsoleView {
    public static void main(String[] args) {
        ViewModel viewModel = new ViewModel(10);
        viewModel.print();
        System.out.println("-----------------------------");
        viewModel.updateGame();
        viewModel.print();
    }
}
