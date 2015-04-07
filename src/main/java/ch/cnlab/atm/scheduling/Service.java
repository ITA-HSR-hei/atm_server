package ch.cnlab.atm.scheduling;

public abstract interface Service {

    public abstract void performService() throws InterruptedException;
}