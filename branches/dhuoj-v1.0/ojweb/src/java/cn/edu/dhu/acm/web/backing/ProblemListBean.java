package cn.edu.dhu.acm.web.backing;

/**
 * Created on Jan 11, 2009
 * @author yhu
 */

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import cn.edu.dhu.acm.persistence.entity.Problem;
import cn.edu.dhu.acm.persistence.dbao.ProblemDBAO;

public class ProblemListBean implements java.io.Serializable {

    // problem entity data base access object.
    private ProblemDBAO dbao;

    // data model for problems.
    private DataModel problems;

    public ProblemListBean() {
        dbao = new ProblemDBAO();
        problems = new ListDataModel();

        // dump problems data from db.
        problems.setWrappedData(dbao.findProblemEntities());
    }

    /**
     * List of Problems in the system.
     *
     * @return Returns the problemss.
     */
    public DataModel getProblems() {
        return problems;
    }


    /**
     * View the specified problem detail.
     *
     * @return outcome
     */
    public String viewProblem() {
        /*
        this.cd = (CD) cdModel.getRowData();
        this.cd = (CD) store.getCDById(cd.getId());

        if ((cd.getCategory() != null) || !"".equals(cd.getCategory())) {
            this.subCategoryList.setRendered(true);

            this.subCategories = getSubcategoriesList(cd.getCategory());
        } else {
            this.subCategoryList.setRendered(false);
        }

        this.editMode = true;
        */
        return "success";
    }
}
