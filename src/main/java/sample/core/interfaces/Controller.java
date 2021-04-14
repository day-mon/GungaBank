package sample.core.interfaces;

import sample.core.objects.bank.User;

public interface Controller
{
    /**
     * @param user
     */
    void initData(User user);

    User getUser();

}
