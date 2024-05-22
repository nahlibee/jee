package modulee;


import java.util.List;

public interface ModuleDAO {
    Module getById(int id_module);
    List<Module> getAll();
    void insert(Module module);
    void update(Module module);
    void delete(int id_module);
}
