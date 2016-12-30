package pengshu.service;

import pengshu.model.Case;
import pengshu.model.NewCase;

import java.util.List;

/**
 * Created by pengshu on 2016/11/3.
 */
public interface CaseService {
    
    List<Case> getCase();

    boolean truncateTableNewCase();

    boolean truncateTableCase();

//    boolean setNewCase(List<Case> list);
}
