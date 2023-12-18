
package entity;

import adt.ListInterface;
import adt.SortedArrayList;
;

/**
 *
 * @author joyii
 */
public class ProgInitializer {
    
    public ListInterface<Programme> initializeProgramme() {
        ListInterface<Programme> pList = new SortedArrayList<>();
        pList.add(new Programme("RSD", "Software Development", 3, null));
        pList.add(new Programme("REI", "Enterprise Information Systems", 3, null));
        pList.add(new Programme("RST", "Interactive Software Technology", 3, null));
        pList.add(new Programme("RDS", "Data Science", 3, null));
        pList.add(new Programme("RSE", "Software Engineering", 3, null));

        return pList;
    }
    
    
   
        
    
}
