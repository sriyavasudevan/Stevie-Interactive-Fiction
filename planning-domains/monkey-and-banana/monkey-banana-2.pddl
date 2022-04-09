(define (domain monkey-banana-2)
    (:requirements :adl :domain-axioms :intentionality)
    (:types thing climbable - thing)
  (:constants BANANA - thing
              STICK - thing
              CHAIR - climbable
              LAB - thing
              HOOK - thing
              PLAYER - thing)
    (:predicates (contains ?x - thing ?y - thing)
                 (portable ?i - thing)
                 (edible ?i - thing)
                 (waveable ?i - thing))
    (:action take
        :parameters (?player - thing ?object - thing ?sameroom - thing)
        :precondition (and (contains ?sameroom ?player)
	                         (contains ?sameroom ?object)   
	                         (not (contains ?player ?object))
                           (portable ?object))
        :effect (and (contains ?player ?object) 
                     (contains ?sameroom ?player))
    )
    (:action get-on
        :parameters (?supporter - climbable ?climber - thing ?sameroom - thing)
        :precondition (and (contains ?sameroom ?supporter)
                           (contains ?sameroom ?climber)
                           (not (contains ?supporter ?climber)))
        :effect ( and (not(contains ?sameroom ?climber))
                          (contains ?supporter ?climber) )
    )
    (:action get-off
        :parameters (?supporter - climbable ?climber - thing ?sameroom - thing)
        :precondition (and (contains ?sameroom ?supporter)
                           (contains ?supporter ?climber))
        :effect ( and (not(contains ?supporter ?climber))
                          (contains ?sameroom ?supporter) )
    )
    (:action wave-gen
        :parameters (?object - thing ?player - thing)
        :precondition (and (contains ?player ?object)
                           (waveable ?object)
                           (portable ?object))
        :effect (contains ?player ?object) 
    )
    (:action wave-spec
        :parameters (?stick - thing ?player - thing ?banana - thing
                                 ?hook - thing ?lab - thing ?chair - thing)
        :precondition   (and (contains ?hook ?banana)
	                           (contains ?player ?stick)
	                           (contains ?chair ?player)
	                           (contains ?lab ?chair)
	                           (waveable ?stick)
	                           (portable ?stick))
        :effect (and (not (contains ?hook ?banana))
                          (contains ?lab ?banana))
    )
)