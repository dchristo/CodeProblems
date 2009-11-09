require 'test/unit'

#We create four different states
#depending on which compass direction
#a rover will be facing.
#The rover will store these states in a hash.

class FacingNorth
  def moveForward(any_rover)
    any_rover.pos_y +=1
  end
  
  def rotateLeft(any_rover)
    any_rover.state = any_rover.select_state(:W)
  end
  
  def rotateRight(any_rover)
    any_rover.state = any_rover.select_state(:E)
  end
  
  def has_reached_edge?(any_rover, any_grid)
    any_rover.pos_y == any_grid._y ? true : false
  end
  
  def to_s
    "N"
  end
  
end

class FacingWest
  def moveForward(any_rover)
    any_rover.pos_x -=1
  end
  
  def rotateLeft(any_rover)
    any_rover.state = any_rover.select_state(:S)
  end
  
  def rotateRight(any_rover)
    any_rover.state = any_rover.select_state(:N)
  end
  
  def has_reached_edge?(any_rover, any_grid)
    any_rover.pos_x == 0 ? true : false
  end
  
  def to_s
    "W"
  end
end

class FacingSouth
  def moveForward(any_rover)
    any_rover.pos_y -=1
  end
  
  def rotateLeft(any_rover)
    any_rover.state = any_rover.select_state(:E)
  end
  
  def rotateRight(any_rover)
    any_rover.state = any_rover.select_state(:W)
  end
  
  def has_reached_edge?(any_rover, any_grid)
    any_rover.pos_y == 0 ? true : false
  end
  
  def to_s
    "S"
  end
end

class FacingEast
  def moveForward(any_rover)
    any_rover.pos_x +=1
  end
  
  def rotateLeft(any_rover)
    any_rover.state = any_rover.select_state(:N)
  end
  
  def rotateRight(any_rover)
    any_rover.state = any_rover.select_state(:S)
  end
  
  def has_reached_edge?(any_rover, any_grid)
    any_rover.pos_x == any_grid._x ? true : false
  end
  
  def to_s
    "E"
  end
end

#Below we create our three commands
#for the sake of calling them later 
#from a hash using M,L,R keys 

class Rover_Move_Forward
  def execute(any_rover)
    any_rover.state.moveForward(any_rover)    
  end
end

class Rover_Rotate_Left
  def execute(any_rover)
    any_rover.state.rotateLeft(any_rover)    
  end
end

class Rover_Rotate_Right
  def execute(any_rover)
    any_rover.state.rotateRight(any_rover)    
  end
end

#Define a grid class with
#upper right-hand corner coordinates
class Grid
  attr_accessor :_x, :_y
  def initialize (x, y)
      @_x, @_y = x, y 
  end    
end

#Define the Rover class which
#encapsulates concrete states and commands inside
#two different hash structures
class Rover

  Commands = {
    :M => Rover_Move_Forward.new,
    :L => Rover_Rotate_Left.new,
    :R => Rover_Rotate_Right.new
  }

  States = {
  :N => FacingNorth.new,
  :W => FacingWest.new,
  :S => FacingSouth.new,
  :E => FacingEast.new    
}
  
  attr_accessor :pos_x, :pos_y, :state
  def initialize (x, y, key)
    @pos_x, @pos_y = x, y
    @state = select_state(key) 
  end
  
  def select_state(key)   
    States[key]  
  end
  
  def get_command(key)
    Commands[key]
  end
  
  def to_s
    "#{@pos_x} " + "#{@pos_y} " + "#{@state}"
  end
  
end

#If a non-[M,L,R] command is detected,
#the rover just sits and waits for
#next valid command

BAD_COMMAND = "Invalid command. Did not execute."

#If the rover is at the edge of the grid, subsequent
#"M" commands that may drive it beyond that edge
#will be blocked. Rotate commands are allowed.

EDGE_OF_GRID = "Reached edge of given grid in this direction."


#Perform some error handling and given
#a signal, a rover and a grid, execute
#the commands taken from the signal

def top_level_runner(a_signal, a_rover, a_grid)
a_signal.split("").each do |command|
  if ["M","L","R"].include?(command)
    if a_rover.state.has_reached_edge?(a_rover, a_grid) && command.eql?("M")
      puts EDGE_OF_GRID
    else
      a_rover.get_command(command.to_sym).execute(a_rover)
    end     
  else
   puts BAD_COMMAND
  end
 end
  puts  a_rover.to_s 
end


#Next, we instantiate a grid and a rover,
#supply a command string and execute the 
#top_level_runner method

grid = Grid.new(5,5)
rov = Rover.new(1,2,:N)
puts rov.to_s
signal = "LMLMLMLMM"

top_level_runner(signal,rov,grid)


#We will create and run some unit tests
class RoverTest < Test::Unit::TestCase
  
  #This is used to capture output
  #of a method to the console. In our case
  #we simply don't do anything with it 
  #so we can have a clean view of our test results
  def capture_stdout(&block)    
    original_stdout = $stdout    
    $stdout = fake = StringIO.new    
    begin      
      yield    
    ensure      
      $stdout = original_stdout    
    end   
    fake.string  
  end
  
   def setup
      @rover = Rover.new(2,2,:N)
    end
  
   def test_one_rotate_left_from_N_to_W
       assert_equal("W", "#{@rover.get_command(:L).execute(@rover)}")
   end
   
   def test_two_execute_M_command
     assert_equal(3, @rover.get_command(:M).execute(@rover))
   end
   
   def test_three_rotate_right_from_N_to_E
     assert_equal("E", "#{@rover.get_command(:R).execute(@rover)}")
   end
   
   def test_four_blocked_facing_North
     assert_equal(true, @rover.state.has_reached_edge?(@rover, Grid.new(2,2)))
   end
   
  def test_five_unblocked_facing_North
    assert_equal(false, @rover.state.has_reached_edge?(@rover, Grid.new(3,3)))
  end
  
  def test_six_execute_a_signal
    capture_stdout { top_level_runner("MMM",@rover,Grid.new(10,10)) }
    assert_equal(5, @rover.pos_y)
  end
  
  #Rover will execute one M command 
  #and will reach the edge of the grid.
  def test_seven_blocked_after_executing_signal
  capture_stdout { top_level_runner("MMM",@rover,Grid.new(3,3)) }
    assert_equal(3, @rover.pos_y)
  end
  
  #Rover will not execute the last
  #command of this signal, due to invalid command
  def test_eight_invalid_command_not_executed
  capture_stdout { top_level_runner("MMo",@rover,Grid.new(10,10)) }
    assert_equal(4, @rover.pos_y)
  end
  
end