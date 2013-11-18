package Parser;
public class ASTPrimary extends SimpleNode {
  private String name;

  /**
   * Constructor.
   * @param id the id
   */
  public ASTPrimary(int id) {
    super(id);
  }


  /**
   * Set the name.
   * @param n the name
   */
  public void setName(String n) {
    name = n;
  }

  /**
   * {@inheritDoc}
   * @see org.javacc.examples.jjtree.eg2.SimpleNode#toString()
   */
  public String toString() {
	  if (name != null)
		  return "Literal: " + name;
	return "Primary";
  }

}
