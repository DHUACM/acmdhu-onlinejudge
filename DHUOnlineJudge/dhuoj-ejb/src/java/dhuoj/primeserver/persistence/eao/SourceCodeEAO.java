package dhuoj.primeserver.persistence.eao;

import dhuoj.primeserver.persistence.entity.SourceCode;

public interface SourceCodeEAO {

    public void addSourceCode(SourceCode src);

    public SourceCode findSourceCode(int slnID);

}