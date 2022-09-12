package features.cards;

import com.google.common.collect.*;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

public class GuavaDeckOfCards {

    private ImmutableList<Card> cards;

    public ImmutableList<Card> getCards() {
        return cards;
    }

    public ImmutableListMultimap<Suit, Card> getCardsBySuit() {
        return cardsBySuit;
    }

    private ImmutableListMultimap<Suit, Card> cardsBySuit;

    public GuavaDeckOfCards()
    {
        this.cards = Card.streamCards().sorted().collect(ImmutableList.toImmutableList());;
        this.cardsBySuit = Multimaps.index(this.cards, Card::suit);
    }

    public ImmutableList<Card> diamonds()
    {
        return this.cardsBySuit.get(Suit.DIAMONDS);
    }

    public ImmutableList<Card> hearts()
    {
        return this.cardsBySuit.get(Suit.HEARTS);
    }

    public ImmutableList<Card> spades()
    {
        return this.cardsBySuit.get(Suit.SPADES);
    }

    public ImmutableList<Card> clubs()
    {
        return this.cardsBySuit.get(Suit.CLUBS);
    }

    public Deque<Card> shuffle(Random random) {
        List<Card> shuffled = new ArrayList<>(this.cards);
        Collections.shuffle(shuffled, random);
        Collections.shuffle(shuffled, random);
        Collections.shuffle(shuffled, random);
        ArrayDeque<Card> cards = new ArrayDeque<>();
        shuffled.forEach(cards::push);
        return cards;
    }

    public Set<Card> deal(Deque<Card> deque, int count) {
        Set<Card> hand = new HashSet<>();
        IntStream.range(0, count).forEach(i -> hand.add(deque.pop()));
        return hand;
    }

//    public Map<Suit, Long> countsBySuit() {
//        return cards.stream().collect(groupingBy(c -> c.suit(), counting()));
//    }

    public Multiset<Suit> countsBySuit() {
        return cards.stream().collect(Multisets.toMultiset(Card::suit, e -> 1, HashMultiset::create));
    }

    public HashMultiset<Rank> countsByRank() {
        return cards.stream().collect(Multisets.toMultiset(Card::rank, e -> 1, HashMultiset::create));
    }

    public static void main(String[] args) {
        var deckCards = new GuavaDeckOfCards();
        System.out.println(deckCards.getCards());

        var diamonds = deckCards.diamonds();
        System.out.println("Diamonds are " + diamonds);

        Deque<Card> cards = deckCards.shuffle(new Random());
        int cnt = 4;
        Set<Card> deals = deckCards.deal(cards, cnt);
        System.out.println("Deals " + cnt + " hands " + deals);

        System.out.println("Count by suits " + deckCards.countsBySuit());
        System.out.println("Count by rank " + deckCards.countsByRank());
    }
}
