package features.cards;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    @Override
    public int compareTo(Card o) {
        return Comparator.comparing(Card::suit).thenComparing(Card::rank).compare(this, o);
    }

    public static Stream<Card> streamCards()
    {
        return Card.cartesianProduct(
                EnumSet.allOf(Suit.class),
                EnumSet.allOf(Rank.class),
                Card::new);
    }

    private static <A, B, R> Stream<R> cartesianProduct(Set<A> set1, Set<B> set2, BiFunction<A, B, R> function)
    {
        return set1.stream().flatMap(first ->
                set2.stream().map(second -> function.apply(first, second)));
    }

    public boolean isDiamonds()
    {
        return this.suit == Suit.DIAMONDS;
    }

    public boolean isHearts()
    {
        return this.suit == Suit.HEARTS;
    }

    public boolean isSpades()
    {
        return this.suit == Suit.SPADES;
    }

    public boolean isClubs()
    {
        return this.suit == Suit.CLUBS;
    }

    public boolean isSameRank(Rank rank)
    {
        return this.rank == rank;
    }
}
