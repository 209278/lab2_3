package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    @Test
    void theSameSeq() {

        SequenceSearcher sequenceSearcher = (key, seq) -> {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(true);
            return builder.build();
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        int[] seq1 = {1, 2, 3};
        int[] seq2 = {1, 2, 3};

        assertEquals(1.0, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void differentSizeOfSequencesWithTheSameElem(){

        SequenceSearcher sequenceSearcher = (key, seq) -> {
            if(key < 3) {
                SearchResult.Builder builder = SearchResult.builder();
                builder.withFound(true);
                return builder.build();
            }else{
                SearchResult.Builder builder = SearchResult.builder();
                builder.withFound(false);
                return builder.build();
            }
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        int[] seq1 = {1, 2, 3, 4};
        int[] seq2 = {1, 2};

        assertEquals(0.5, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void differentSizeOfSequencesWithDifferentElem(){

        SequenceSearcher sequenceSearcher = (key, seq) -> {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(false);
            return builder.build();
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        int[] seq1 = {1, 2, 3, 4};
        int[] seq2 = {5, 6};

        assertEquals(0.0, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void bothEmptySeq(){

        SequenceSearcher sequenceSearcher = (key, seq) -> {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(false);
            return builder.build();
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        int[] seq1 = {};
        int[] seq2 = {};

        assertEquals(1.0, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void TheSameSizeWithDifferentValues(){

        SequenceSearcher sequenceSearcher = (key, seq) -> {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(false);
            return builder.build();
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        int[] seq1 = {1, 2, 3, 4};
        int[] seq2 = {5, 6, 7, 8};

        assertEquals(0.0, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

}
