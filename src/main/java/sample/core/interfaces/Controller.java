package sample.core.interfaces;

import sample.core.objects.bank.User;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;

public interface Controller
{
    void initData(User user, StageHandler stageHandler, FileHandler fileHandler);

    User getUser();

}
