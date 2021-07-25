package il.ac.hit.controller;

import il.ac.hit.model.IProductsDAO;

/**
 * basic controller, UserControlller extends from here.
 *
 */
public class BasicController extends AbstractController{

	/**
	 * constructor.
	 * @param dao
	 */
    public BasicController(IProductsDAO dao) {
        super(dao);
    }

    @Override
    public void init() {

    }
}
