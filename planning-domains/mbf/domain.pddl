(define (domain monkey-banana-3)
	(:requirements :adl :domain-axioms :intentionality)
	(:types edible climbable rigid room player - thing)
  (:constants BANANA - edible
  						STICK - thing
  						CHAIR - thing
  						LAB - room
  						HOOK - rigid
  						PLAYER - player)
	(:predicates (contains ?x - thing ?y - thing)
							 (portable ?x - thing)
							 (waveable ?x - thing)
							 (eaten ?x - player ?y - thing)
							 (climbable ?x - thing)
							 (edible ?x - thing))

  (:action take
	 	:parameters	(?object - thing)
	 	:precondition (and (contains LAB ?object)
	 										 (contains LAB PLAYER)
	 										 (not (contains PLAYER ?object))
	 										 (portable ?object))
	 	:effect (contains PLAYER ?object)
	)

	(:action get-on
		:parameters (?object - thing)
		:precondition ( and (contains LAB ?object)
												(climbable ?object)
												(not (contains ?object PLAYER)))
		:effect (and (contains ?object PLAYER)
								 (not (contains LAB PLAYER)))
	)

	(:action wave
		:parameters (?object - thing)
		:precondition ( and (contains PLAYER ?object)
												(portable ?object)
												(waveable ?object)
												(not(contains LAB PLAYER)))
		:effect (contains LAB BANANA)
	)

	(:action get-off
		:parameters (?object - thing)
		:precondition (and (contains ?object PLAYER)
											 (climbable ?object)
											 (contains LAB ?object))
		:effect (and (contains LAB PLAYER)
								 (not (contains ?object PLAYER)))
	)

	(:action eat
		:parameters (?object - thing)
		:precondition (and (contains PLAYER ?object)
											 (not (eaten PLAYER ?object))
											 (edible ?object))
		:effect (eaten PLAYER ?object)
	)
)