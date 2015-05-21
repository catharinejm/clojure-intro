(ns seqs)

;; seq fn - returns the sequence version of your collection, or nil when it's empty
(seq ()) ;=> nil
(seq '(1 2 3)) ;=> (1 2 3)

(seq []) ;=> nil
(seq [1 2 3]) ;=> (1 2 3)
;; notice it becomes listy!

(seq #{}) ;=> nil
(seq #{1 2}) ;=> (1 2)

(seq {}) ;=> nil
(seq {1 2, 3 4}) ;=> ([1 2] [3 4])
;; map pairs are grouped in 2-element vectors

(seq nil) ;=> nil

;; Some collection types outside of Clojure's standard library can be seq'd too
(seq "hello") ;=> (\h \e \l \l \o)
(seq (int-array [1 2 3])) ;=> (1 2 3)
(seq (java.util.Arrays/asList (object-array [1 2 3]))) ;=> (1 2 3)

;; In clojure we usually use seq to test if a collection is empty.
;; If it's not, we get a seq, which is truthy!
(if (seq my-coll)
  (do-more-stuff)
  done)

;; All of the sequence library functions seq the input collection
;; so we can use them almost entirely interchangeably!
(map inc (list 1 2 3)) ;=> (2 3 4)
(map inc [1 2 3]) ;=> (2 3 4)

(map + (list 1 2) [30 15]) ;=> (31 17)

;; Almost...
(map + #{1 2} #{30 15}) ;=> (16 32) Sets aren't ordered!
(map inc {1 2 3 4}) ;=> ERROR: can't cast vector to number. Oh yeah... (seq {1 2 3 4}) is ([1 2] [3 4])

;; conj
(conj '(:foo :bar) 42) ;=> (42 :foo :bar)
(conj [:foo :bar] 42) ;=> [:foo :bar 42]
(conj {:foo :bar} [:baz 10]) ;=> {:baz 10, :foo :bar}
(conj #{:foo :bar} 42) ;=> #{:bar :foo 42}
;; conj adds the new element in the manner most efficient for the underlying datastructure
;; Can be confusing at first, but you'll get used to it

;; maps usually use assoc
(assoc {:foo :bar} :baz 10) ;=> {:baz 10, :foo :bar}
;; or merge
(merge {:foo :bar} {:baz 10}) ;=> {:baz 10, :foo :bar}

;; cons prepends an element
(cons 1 '(2 3)) ;=> (1 2 3)
(cons 1 [2 3]) ;=> (1 2 3)
;; Why did that return a list? Or did it return a seq...

