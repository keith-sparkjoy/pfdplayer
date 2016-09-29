(ns pfdplayer.vm.pfd)

(defn pfd->vm
  [pfd {:keys [begin-x begin-y step-width step-height nva-offset]} zoom]
  {:viewport-height (+ begin-y (* step-height (count (:steps pfd))))
   :viewport-width (+ begin-x
                      (* step-width
                         (inc (apply max
                                     (map #(count (:nva %))
                                          (:steps pfd))))))
   :steps (map-indexed
           (fn [n step]
             (let [y (+ begin-y (* n step-height))]
               (assoc step
                      :x begin-x
                      :y y
                      :nva (map-indexed
                            (fn [n step]
                              (assoc step
                                     :x (+ begin-x
                                           nva-offset
                                           (* (inc n) step-width))
                                     :y y))
                            (:nva step)))))
           (:steps pfd))
   :zoom zoom})

