// @include
class pnode_t<T> {  
  public T data;
  public pnode_t<T> next, jump;
  
  pnode_t(T dt, pnode_t<T> n, pnode_t<T> j) {
    data = dt;
    next = n;
    jump = j;
  }
}
//@exclude

