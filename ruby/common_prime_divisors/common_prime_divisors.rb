# https://codility.com/demo/results/demoHJ7BQ8-QTS/
class CommonPrimeDivisors

  def has_common_prime_divisors? a, b
    return true if a == b

    prime_divisors_a = prime_numbers_from divisors_for a
    prime_divisors_b = prime_numbers_from divisors_for b
    
    prime_divisors_a == prime_divisors_b
  end

  private

  def divisors_for value
    1.upto(value).select { |i| value % i == 0 }
  end

  def prime_numbers_from array
    array.select { |devisor| prime_number? devisor }
  end

  def prime_number? value
    return false if value < 2
    i = 2
    while i * i <= value do
      return false if value % i == 0
      i += 1
    end
    true
  end

end