package modulee;

import java.util.List;

public interface ElementDAO {
    Element getById(int id_element);
    List<Element> getByModuleId(int id_module);
    List<Element> getAll();
    void insert(Element element);
    void update(Element element);
    void delete(int id_element);
}
