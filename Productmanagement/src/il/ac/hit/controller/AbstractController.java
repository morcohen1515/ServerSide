package il.ac.hit.controller;

import il.ac.hit.model.IProductsDAO;

/** abstract controller
 * all controllers extends from here.
 * */
public abstract class AbstractController {

    protected IProductsDAO dao;
    /**
     * constructor.
     * @param dao
     */
    AbstractController(IProductsDAO dao) {
        this.dao = dao;
    }

    public abstract void init();
}
