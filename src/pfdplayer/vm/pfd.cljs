(ns pfdplayer.vm.pfd)

(defn- add-end
  [steps]
  (conj steps {:t :end}))

(defn pfd->vm
  [pfd {:keys [begin-x begin-y step-width step-height nva-offset]} zoom]
  (let [steps (map-indexed
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
                                (add-end (:nva step))))))
               (add-end (:steps pfd)))]
    {:viewport-width (+ begin-x
                        (* step-width
                           (inc (apply max
                                       (map #(count (:nva %)) steps)))))
     :viewport-height (+ begin-y (* step-height (count steps)))
     :steps steps
     :zoom zoom}))

