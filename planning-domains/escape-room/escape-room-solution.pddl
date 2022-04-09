(define (plan escape-room-problem-solution)
  (:problem escape-room-problem)
  (:steps (open CUPBOARD STOOL ROOM PLAYER)
          (take PLAYER STOOL ROOM)))