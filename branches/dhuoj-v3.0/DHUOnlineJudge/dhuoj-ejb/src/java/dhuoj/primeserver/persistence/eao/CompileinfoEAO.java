package dhuoj.primeserver.persistence.eao;

import dhuoj.primeserver.persistence.entity.Compileinfo;

public interface CompileinfoEAO {

    public void addCompileInfo(Compileinfo compileinfo);

    public Compileinfo findCompileInfo(int slnID);

}
