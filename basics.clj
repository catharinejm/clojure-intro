(ns basics)

;; literals

;; numbers
42 0x2A 052 2r101010 13r33 42N
4.2 21/5 0.042e2 4.2M

;; Symbols
foo-bar high^hat &whoa! ten% name.spaced/symbol hello?

;; Keywords
:foo-bar :high^hat :&whoa! :ten% :name.spaced/keyword :hello? ::this-ns-keyword

;; Booleans
true false

;; nil
nil ; nil is falsy!

;; Characters
\a \b \c \return \space \newline \backspace

;; Strings
"Hello, I am a string"

;; Regex
#"this is(?: definitely)? a regex"

;; Lists
() (1 2 3) ("list" of :stuff (more-stuff 234))

;; Vectors
[1 2 3] [all [:data-types] "are" (heterogeneous)]

;; Maps
{:foo 1 :bar 2}
{:foo 1, :bar 2} ; commas are whitespace!
{1 "value1", "2" :value2, {:map fun} {}} ; anything can be a key

;; Sets
#{1 2 3} #{foo :bar "baz"}

;; Quoting
'foo '(1 2 3)

;; Syntax quoting (for later)
`foo `(foo ~bar) ~(foo ~@my-list)

;; Basic forms

;; def
(def x 10)

;; symbols are implicitly evaluated
x ;=> 10
foo-bar ;; Undefined symbol error!

;; quote
(quote foo-bar) ;=> foo-bar
'foo-bar ;=> foo-bar
;;lists need to be quoted too, or they're invocations
(foo 10) ;=> unknown function foo
'(foo 10) ;=> (foo 10)
;; or use the list function
(list foo 10) ;=> (foo 10)

;; fn
(fn [x] (+ x 10))
((fn [x] (+ x 10)) 32) ;=> 42

;; defn
(def func
  (fn [x] (+ x 10)))
(func 32) ;=> 42

(defn func [x]
  (+ x 10))
(func 17) ;=> 27


;; if
(if condition
  true-branch
  false-branch)

;; false-branch is optional
(if something
  true-branch)

;; nil is falsy
(if nil
  won't-see-me
  will-see-me)

;; Every value except nil and false are truthy!
(if 0
  yep!
  nope...)

(if :a-keyword
  still-yep!
  and-still-nope...)

;; cond
(cond
  some-condition
  (eval'd if it was true)

  another-condition
  (eval'd if the above was false, but this one isn't)

  :else
  default)
;; nothing special about :else, it's just truthy because everything is!
;; We could use any old truthy value for our default

;; do
(do
  expr1
  (another expr)
  third-thing) ; evals to last expression! others must be for side effects

;; let

(let [x 10]
  (+ x 10)) ;=> 20
;; incremental
(let [x 10
      y (+ x 5)]
  (+ x y)) ;=> 10 + 15 = 25

(let [x 10
      x (* 2 x)]
  x) ;=> 20

;; letfn
(letfn [(func [x] (+ x 10))]
  (func 32)) ;=> 42
;; subtly different from let - all symbols reserved simultaneously, not incremental
(letfn [(is-even? [n] (or (zero? n) (is-odd? (dec n))))
        (is-odd? [n] (not (is-even? n)))]
  (is-even? 10)) ;=> true

;; but..
(let [is-even? (fn [n] (or (zero? n) (is-odd? (dec n))))
      is-odd? (fn [n] (not (is-even? n)))]
  (is-even? 10))
;; COMPILE ERROR: Undefined symbol 'is-odd?

