(p)
(define (domain simple)
  (:requirements :adl :domain-axioms :intentionality)
  (:types item)
  (:constants banana - item
  						stick - item)
  (:predicates (player-has ?item - item))

  (:action take
    :parameters (?i - item)
    :precondition (not (player-has ?i))
    :effect (player-has ?i))
    )
