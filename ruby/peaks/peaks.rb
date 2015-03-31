# https://codility.com/demo/results/demo9HH89K-SHA/
class Peaks

  def initialize array
    @array = array
  end

  def can_be_divided_into
    return 0 if @array.empty?

    divisors = divisors_for @array.length
    peak_indexes = peak_indexes_in @array
    return 0 if peak_indexes.empty?

    divisors.reverse.each do |divisor|
      step = @array.length / divisor
      return divisor if step > 1 && parts_would_have_peaks?(@array, peak_indexes, step)
    end
    0
  end

  private

  def divisors_for value
    1.upto(value).select {|i| value % i == 0 }
  end

  def peak_indexes_in array
    peak_indexes = []
    peak_indexes = array.map.with_index do |el, i|
      i if i > 0 && i < array.length - 1 && peak_indexes.last != i && el > array[i - 1] && el > array[i + 1]
    end
    peak_indexes.compact
  end

  def parts_would_have_peaks? array, peak_indexes, step
    have_peaks = true
    (array.length / step).times do |i|
      from_index = i * step
      to_index = (i + 1) * step
      have_peaks = false unless peak_within_interval? array, peak_indexes, from_index, to_index
    end
    have_peaks
  end

  def peak_within_interval? array, peak_indexes, from_index, to_index
    peak_indexes.each { |peak_index| return true if peak_index >= from_index && peak_index < to_index }
    false
  end

end