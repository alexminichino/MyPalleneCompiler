package visitor;

public interface Element {
    <T, P> T accept(Visitor<T, P> visitor, P arg);

}
